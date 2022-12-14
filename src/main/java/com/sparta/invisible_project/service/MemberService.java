package com.sparta.invisible_project.service;

import com.sparta.invisible_project.dto.*;
import com.sparta.invisible_project.model.Authority;
import com.sparta.invisible_project.model.Member;
import com.sparta.invisible_project.repository.MemberRepository;
import com.sparta.invisible_project.repository.RefreshTokenRepository;
import com.sparta.invisible_project.security.JwtFilter;
import com.sparta.invisible_project.security.MemberDetails;
import com.sparta.invisible_project.security.RefreshToken;
import com.sparta.invisible_project.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username);
        Member member = memberRepository
                .findByUsername(username)
                .orElseThrow(
                        ()->new UsernameNotFoundException(username+"??? ?????? ??? ????????????")
                );
        System.out.println(member);
        return new MemberDetails(member);
    }

    // ????????????
    @Transactional
    public ResponseDto<?> createAccount(SignupReqDto signupReqDto) {
        String username = signupReqDto.getUsername();
        String password = signupReqDto.getPassword();
        String passwordConfirm = signupReqDto.getPasswordConfirm();
        if(memberRepository.existsByUsername(username)) {
            throw new RuntimeException("?????? ????????? ???????????????");
        }
        if(!password.equals(passwordConfirm)) {
            throw new RuntimeException("??????????????? ???????????? ????????? ???????????? ????????????");
        }
        Member member = new Member(username, passwordEncoder.encode(password), Authority.ROLE_USER);
        return ResponseDto.success(memberRepository.save(member));
    }

    // ?????????
    @Transactional
    public ResponseEntity<?> loginAccount(LoginReqDto loginReqDto) {

        // ????????? ?????? Security ????????? ?????? ??????
        UsernamePasswordAuthenticationToken authenticationToken = loginReqDto.toAuthentication();

        // ??????
        // (Security Depth : SecurityContextHolder > Context > Authentication > UsernamePasswordAuthenticationToken > MemberDetails)
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        Member member = memberRepository.findByUsername(loginReqDto.getUsername()).orElse(null);
        // ?????? ????????? ???????????? JWT ?????? ??????
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        
        // refresh ?????? db ??????
        RefreshToken refreshToken = RefreshToken.builder()
                // key ????????? ?????? ??????
                .key(authentication.getName())
                // value ???????????? ??? ???????????? ??????
                .value(tokenDto.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        
        // ??????????????? ????????? ?????? ????????? ?????? ??????
        HttpHeaders httpHeaders = new HttpHeaders();
        // ????????? Authorization ?????? ????????? Jwt ??????????????? value??? Bearer ????????? ????????? ????????? ?????? ????????? ??????
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, JwtFilter.BEARER_PREFIX + tokenDto.getAccessToken());
        httpHeaders.add("Refresh-Token", tokenDto.getRefreshToken());

        // ?????? ??????
        return new ResponseEntity<>(ResponseDto.success(member), httpHeaders, HttpStatus.OK);
    }

    // ?????? ?????????
    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // refresh token ??????
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException(("Refresh Token??? ???????????? ????????????"));
        }

        // accesss token?????? member id ????????????
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // ??????????????? member id??? ???????????? refresh token ??? ?????????. UsernamePasswordAuthenticationToken.getName(Principal.getName())
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(()->new RuntimeException("???????????? ??? ??????????????????"));

        // refresh token ??????
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("????????? ?????? ????????? ???????????? ????????????");
        }

        // ?????? ??????
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // ?????? ????????? refresh ?????? ?????? db ????????????
        RefreshToken refreshRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(refreshRefreshToken);

        // ?????? ?????? ??????????????? ????????????????????? ????????????
        return tokenDto;
    }
}
