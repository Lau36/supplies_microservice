package com.example.transaction_microservice.infrastructure.adapters.output.persistence;

import com.example.transaction_microservice.domain.ports.output.IUserPort;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@AllArgsConstructor
public class UserPortImpl implements IUserPort {

    @Override
    public Long getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Long.parseLong(auth.getDetails().toString());
    }
}
