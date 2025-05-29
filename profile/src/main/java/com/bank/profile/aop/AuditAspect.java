package com.bank.profile.aop;


import com.bank.profile.dto.ProfileDto;
import com.bank.profile.services.interfaces.AuditService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditService auditService;

    @AfterReturning(pointcut = "execution(* com.bank.profile.services.interfaces.ProfileService.create(..))", returning = "result")
    public void logProfileCreation(ProfileDto result) {
        auditService.logChange("PROFILE", "CREATE", result.getId(), "Profile created with email: " + result.getEmail());
    }

    @AfterReturning(pointcut = "execution(* com.bank.profile.services.interfaces.ProfileService.update(..))", returning = "result")
    public void logProfileUpdate(ProfileDto result) {
        auditService.logChange("PROFILE", "UPDATE", result.getId(), "Profile updated with email: " + result.getEmail());
    }

    @Before("execution(* com.bank.profile.services.interfaces.ProfileService.delete(..)) && args(id)")
    public void logProfileDelete(Long id) {
        auditService.logChange("PROFILE", "DELETE", id, "Profile deleted");
    }
}