package org.zerock.ex00.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberVO implements UserDetails {
    private String uid;
    private String upw;
    private String uname;
    private String email;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private List<MemberAuthVO> authVOList;


    @Override
    //GrantedAuthority : 부여된 권한이 Collection으로 -> SimpleGrantedAuthority...
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authVOList == null || authVOList.isEmpty()) {
            return null;
        }
        return authVOList.stream()
                .map(authVO -> new SimpleGrantedAuthority(authVO.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.upw;
    }

    @Override
    public String getUsername() {
        return this.uid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //만료된 것 아니지..? -> Credential : 자격
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
