package org.sadr.config;

import org.junit.Before;
import org.sadr._core.utils.Digester;
import org.sadr.utils.MockObjectInstances;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin._type.TtUserStatus;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class AbstractContextControllerTests {


    @Autowired
    protected MockHttpSession session;

    protected MockMvc mockMvc;

    protected UserService userService;

    @Autowired
    protected AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext;

    protected User signinAsAdmin() {
        User user = this.userService.authenticateEAndLogin(MockObjectInstances._ADMIN_USER_NAME, Digester.digestSHA1(MockObjectInstances._ADMIN_PASSWORD), session);
        if (user == null) {
            user = MockObjectInstances.getInstance().getUser(false);
            user.setUsername(MockObjectInstances._ADMIN_USER_NAME);
            user.setPassword(Digester.digestSHA1(MockObjectInstances._ADMIN_PASSWORD));
            user.setLevel(TtUserLevel.Administrator);
            user.setFirstName("کاربر");
            user.setLastName("مدیر1");
            user.setStatus(TtUserStatus.Active);
            this.userService.save(user);
            user = this.userService.authenticateEAndLogin(MockObjectInstances._ADMIN_USER_NAME, Digester.digestSHA1(MockObjectInstances._ADMIN_PASSWORD), session);
        } else if (user.getStatus() == TtUserStatus.Deactive) {
            user.setStatus(TtUserStatus.Active);
            this.userService.update(user);
        }
        return
                user;
    }

    protected User signinAsLogManager() {
        User user = this.userService.authenticateEAndLogin(MockObjectInstances._LOGMANAGER_USER_NAME, Digester.digestSHA1(MockObjectInstances._LOGMANAGER_PASSWORD), session);
        user.setStatus(TtUserStatus.Active);
        user.setIsNeedToChangePassword(false);
        return user;
    }

    @Before
    public void setUp() {

        userService = annotationConfigWebApplicationContext.getBean(UserService.class);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(annotationConfigWebApplicationContext)
                .build();
    }

}
