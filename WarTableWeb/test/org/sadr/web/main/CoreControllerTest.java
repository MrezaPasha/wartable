package org.sadr.web.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sadr._core.utils.Digester;
import org.sadr.config.AbstractContextControllerTests;
import org.sadr.utils.MockObjectInstances;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main.admin.user.user.User;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CoreControllerTest extends AbstractContextControllerTests {

    ////////////////////
    private final String _PANEL_URL = "/panel/core";

    //========================================== pPanelDashboardTest
    @Test
    public void pPanelDashboardTest_getPageForAdmin() throws Exception {
        User security = userService.findByUsername(MockObjectInstances._ADMIN_USER_NAME);
        if (security.getIsNeedToChangePassword()) {
            security.setIsNeedToChangePassword(false);
            this.userService.update(security);
        }

        userService.authenticateEAndLogin(MockObjectInstances._ADMIN_USER_NAME, Digester.digestSHA1(MockObjectInstances._ADMIN_PASSWORD), session);
        this.mockMvc.perform(get("/panel")
                .session(session)
                .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_dashboard.getCode()))
                .andExpect(model().size(3))
                .andExpect(model().hasNoErrors())
        ;
    }

    @Test
    public void pPanelDashboardTest_getPageForLogeManager() throws Exception {
        User security = userService.findByUsername(MockObjectInstances._LOGMANAGER_USER_NAME);
        if (security.getIsNeedToChangePassword()) {
            security.setIsNeedToChangePassword(false);
            this.userService.update(security);
        }
        userService.authenticateEAndLogin(MockObjectInstances._LOGMANAGER_USER_NAME, Digester.digestSHA1(MockObjectInstances._LOGMANAGER_PASSWORD), session);
        this.mockMvc.perform(get("/panel")
                .session(session)
                .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_dashboard.getCode()))
                .andExpect(model().size(3))
                .andExpect(model().hasNoErrors())
        ;
    }

    @Test
    public void pPanelDashboardTest_accessDeniedForGuest() throws Exception {
        session.removeAttribute("sUser");
        this.mockMvc.perform(get("/panel")
                .session(session)
                .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
                .andExpect(model().size(1))
                .andExpect(model().hasNoErrors())
        ;
    }

    //========================================== pPanelDashboardTest
    @Test
    public void fIndex_getPageForAdmin() throws Exception {
        userService.authenticateEAndLogin(MockObjectInstances._ADMIN_USER_NAME, Digester.digestSHA1(MockObjectInstances._ADMIN_PASSWORD), session);
        this.mockMvc
                .perform(get("/")
                        .session(session)
                        .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/panel"))
        ;
    }

    @Test
    public void fIndex_getPageForGuest() throws Exception {
        session.removeAttribute("sUser");
        this.mockMvc
                .perform(get("/")
                        .session(session)
                        .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/signin"))

        ;
    }

    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# getDateXTest  #
    //============================= getDateXTest_accessTo  #
    @Test
    public void getDateXTest_accessTo() throws Exception {
        //----------------------- Guest

        //----------------------- LogManager

        //----------------------- Admin

    }
    //............................. getDateXTest_accessTo  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# fIndexTest  #
    //============================= fIndexTest_accessTo  #
    @Test
    public void fIndexTest_accessTo() throws Exception {
        //----------------------- Guest

        //----------------------- LogManager

        //----------------------- Admin

    }
    //............................. fIndexTest_accessTo  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# fRobotsTxtTest  #
    //============================= fRobotsTxtTest_accessTo  #
    @Test
    public void fRobotsTxtTest_accessTo() throws Exception {
        //----------------------- Guest

        //----------------------- LogManager

        //----------------------- Admin

    }
    //............................. fRobotsTxtTest_accessTo  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pPanelDashboardTest  #
    //============================= pPanelDashboardTest_accessTo  #
    @Test
    public void pPanelDashboardTest_accessTo() throws Exception {
        //----------------------- Guest

        //----------------------- LogManager

        //----------------------- Admin

    }
    //............................. pPanelDashboardTest_accessTo  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# getDateTimeXTest  #
    //============================= getDateTimeXTest_accessTo  #
    @Test
    public void getDateTimeXTest_accessTo() throws Exception {
        //----------------------- Guest

        //----------------------- LogManager

        //----------------------- Admin

    }
    //............................. getDateTimeXTest_accessTo  #

}
