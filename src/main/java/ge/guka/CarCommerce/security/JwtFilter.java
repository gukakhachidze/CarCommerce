package ge.guka.CarCommerce.security;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = authHeader.substring(7);
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secretKey.getBytes());

            if (!signedJWT.verify(verifier)) {
                filterChain.doFilter(request,response);
                return;
            }

            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expirationTime == null || expirationTime.before(new Date())){
                System.out.println("JWT is expired"); // TODO: Replace with proper logging. =)
                logger.error("Error during JWT extraction");
                filterChain.doFilter(request,response);
                return;
            }

            String username = signedJWT.getJWTClaimsSet().getSubject();
            List<String> roles = signedJWT.getJWTClaimsSet().getStringListClaim("roles");

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username,null,
                            roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                                    .collect(Collectors.toSet()));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch (Exception exception) {
            System.out.println("Error during jwt extraction"); // TODO: Replace with proper logging.
            logger.error("Error during jwt extraction", exception);
        }

        filterChain.doFilter(request,response);

    }
}
