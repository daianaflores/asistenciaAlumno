// package asistenciaalumnos.app.configs;

// import org.springframework.core.MethodParameter;
// import org.springframework.security.core.Authentication;
// import org.springframework.rpo.core.context.SecurityContextHolder;
// import org.springframework.web.bind.support.WebArgumentResolver;
// import org.springframework.web.bind.support.WebDataBinderFactory;
// import org.springframework.web.context.request.NativeWebRequest;
// import org.springframework.web.method.support.HandlerMethodArgumentResolver;
// import org.springframework.web.method.support.ModelAndViewContainer;

// import java.util.Set;
// import java.util.stream.Collectors;

// public class UserDetailsArgumentResolver implements HandlerMethodArgumentResolver {
//     @Override
//     public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
//                                   NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//         if (supportsParameter(methodParameter)) {
//             return createUserDetails(webRequest);
//         }
//         return WebArgumentResolver.UNRESOLVED;
//     }

//     @Override
//     public boolean supportsParameter(MethodParameter methodParameter) {
//         return methodParameter.getParameterAnnotation(CurrentUser.class) != null
//                 && methodParameter.getParameterType().equals(UserDetails.class);
//     }

//     private Object createUserDetails(NativeWebRequest webRequest) {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         return (UserDetails) authentication.getPrincipal();
//     }
// }
