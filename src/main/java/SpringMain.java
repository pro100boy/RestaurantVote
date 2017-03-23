import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Galushkin Pavel
 * 04.03.2017
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-mvc.xml"))
        {
            System.out.println("\n>>>>>>>>>  Bean definition names: >>>>>>>>>>");
            Arrays.asList(appCtx.getBeanDefinitionNames()).stream().forEach(System.out::println);
            System.out.println(">>>>>>>>>>>>>>>>>>>>\n");

            /*UserService userService = appCtx.getBean(UserService.class);
            userService.getAll().stream().forEach(System.out::println);*/

            /*System.out.println();
            System.out.println(userService.getWithVotes(100_000));*/

            String name = appCtx.getMessage("exception.menu.duplicate_in_restaurant",
                    null, LocaleContextHolder.getLocale());

            System.out.println("message (getLocale) : " + name);
        }
    }
}
