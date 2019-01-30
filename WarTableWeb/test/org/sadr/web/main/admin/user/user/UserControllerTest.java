package org.sadr.web.main.admin.user.user;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sadr.config.AbstractContextControllerTests;
import org.sadr.utils.MockObjectBuilder;
import org.sadr.utils.MockObjectInstances;
import org.sadr.web.main._core._type.TtTile___;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends AbstractContextControllerTests {

    ////////////////////
    private final String _PANEL_URL = "/panel/user";

    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pCreateTest  #
    //============================= pCreateTest_accessTo  #
    @Test
    public void pCreateTest_accessTo() throws Exception {
        //----------------------- Guest
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/create")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/create")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_e_401.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeDoesNotExist("uglist", "user", "action"))
        ;

        //----------------------- Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/create")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_user_create.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("uglist", "user", "action"))
        ;
    }
    //............................. pCreateTest_accessTo  #

    //============================= pCreateTest_post_submit  #
    @Test
    public void pCreateTest_post_submit() throws Exception {
        User objUser = MockObjectInstances.getInstance().getUser(true);

        //----------------------- Check Access
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/create", objUser)
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
        ;

        this.signinAsAdmin();

        //----------------------- First Create
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/create", objUser)
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern(_PANEL_URL + "/edit/*"))
                .andExpect(flash().attributeExists("notice"))
        ;


        //----------------------- Repeat Create
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/create", objUser)
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("notice", "user"))
        ;
    }
    //............................. pCreateTest_post_submit  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pEditTest  #
    //============================= pEditTest_accessTo  #
    @Test
    public void pEditTest_accessTo() throws Exception {
        //----------------------- Guest
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/edit/0")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        User realUser = MockObjectInstances.getInstance().getRealUser();

        //----------------------- LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/edit/" + realUser.getId())
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_e_401.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeDoesNotExist("uglist", "user", "action"))
        ;

        //----------------------- Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/edit/" + realUser.getId())
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_user_edit.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("uglist", "user", "action"))
        ;
    }
    //............................. pEditTest_accessTo  #

    //============================= pEditTest_notFound  #
    @Test
    public void pEditTest_notFound() throws Exception {

        //-----------------------
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/edit/0")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl(_PANEL_URL + "/list"))
                .andExpect(flash().attributeExists("notice"))
        ;
    }
    //............................. pEditTest_notFound  #

    //============================= pEditTest_post_submit  #
    @Test
    public void pEditTest_post_submit() throws Exception {

        //----------------------- Check Access
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/edit", MockObjectInstances.getInstance().getUser(true))
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
        ;

        User realUser = MockObjectInstances.getInstance().getRealUser();
        this.signinAsAdmin();

        //----------------------- Success Edit
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/edit", realUser)
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern(_PANEL_URL + "/edit/*"))
                .andExpect(flash().attributeExists("notice"))
        ;

        //----------------------- Not Found  User
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/edit", MockObjectInstances.getInstance().getUser(true))
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("notice", "user"))
        ;

    }

    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pEditYourUserTest  #
    //============================= pEditYourUserTest_accessTo  #
    @Test
    public void pEditYourUserTest_accessTo() throws Exception {
        //----------------------- Guest
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/your-edit")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/your-edit")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_user_yourEdit.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("user", "action"))
        ;

        //----------------------- Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/your-edit")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_user_yourEdit.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("user", "action"))
        ;

    }
    //............................. pEditYourUserTest_accessTo  #

    //============================= pEditYourUserTest_post_submit  #
    @Test
    public void pEditYourUserTest_post_submit() throws Exception {

        //----------------------- Check Access
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/your-edit", MockObjectInstances.getInstance().getClientUser())
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
        ;

        User user = this.signinAsAdmin();

        //----------------------- Success Edit
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/your-edit", user)
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(_PANEL_URL + "/your-edit"))
                .andExpect(flash().attributeExists("notice"))
        ;

        //----------------------- Not Found User
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/your-edit", MockObjectInstances.getInstance().getClientUser(true))
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("notice", "user"))
        ;

    }
    //............................. pEditYourUserTest_post_submit  #

    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pChangeUserPasswordTest  #
    //============================= pChangeUserPasswordTest_accessTo  #
    @Test
    public void pChangeUserPasswordTest_accessTo() throws Exception {
        //----------------------- Guest
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/user-pass/0")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- LogManager
        User user = this.signinAsLogManager();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/user-pass/" + user.getId())
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/user-pass/" + user.getId())
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_user_changeUserPass.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("user", "action"))
        ;

    }
    //............................. pChangeUserPasswordTest_accessTo  #

    @Test
    public void pChangeUserPasswordTest_post_submit() throws Exception {

        //----------------------- Check Access
        this.mockMvc
                .perform(
                        post(_PANEL_URL + "/user-pass")
                                .param("newPassword", "121212")
                                .param("repassword", "121212")
                                .param("id", "1")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
        ;

    }
    //............................. pEditTest_post_submit  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pListTest  #
    //============================= pListTest_accessTo  #
    @Test
    public void pListTest_accessTo() throws Exception {
        //----------------------- Guest
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/list")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/list")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/list")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_user_list.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("searchId", "cols"))
        ;
    }
    //............................. pListTest_accessTo  #

    //============================= pListTest_post_submit  #
    @Test
    public void pListTest_post_ajax_submit() throws Exception {

        //----------------------- Check Access
        this.mockMvc
                .perform(
                        post(_PANEL_URL + "/list")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("ap", MockObjectInstances.getInstance().getListAP())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("nok"))
                .andExpect(jsonPath("$.messages", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.result", Matchers.anEmptyMap()))
        ;

        //----------------------- Success List LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        post(_PANEL_URL + "/list")
                                .session(session)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("ap", MockObjectInstances.getInstance().getListAP())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("nok"))
                .andExpect(jsonPath("$.messages", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.result", Matchers.anEmptyMap()))
        ;

        //----------------------- Success List Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        post(_PANEL_URL + "/list")
                                .session(session)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("ap", MockObjectInstances.getInstance().getListAP())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("ok"))
                .andExpect(jsonPath("$.result").isNotEmpty())
        ;
    }
    //............................. pListTest_post_submit  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pTrashTest  #
    //============================= pTrashTest_accessTo  #
    @Test
    public void pTrashTest_ajax_accessTo() throws Exception {
        //----------------------- Guest
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/trash/0")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("nok"))
                .andExpect(jsonPath("$.messages", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.result", Matchers.anEmptyMap()))
        ;

        //----------------------- LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/trash/0")
                                .session(session)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("nok"))
                .andExpect(jsonPath("$.messages", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.result", Matchers.anEmptyMap()))
        ;
    }
    //............................. pTrashTest_accessTo  #

    //============================= pTrashTest_do  #
    @Test
    public void pTrashTest_ajax_do() throws Exception {

        //----------------------- Not Found
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/trash/0")
                                .session(session)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("nok"))
                .andExpect(jsonPath("$.messages", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.result", Matchers.anEmptyMap()))
        ;

        //----------------------- Success
        MvcResult mvcResult = this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/create", MockObjectInstances.getInstance().getClientUser(true))
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern(_PANEL_URL + "/edit/*"))
                .andExpect(flash().attributeExists("notice"))
                .andReturn();

        String viewName = mvcResult.getModelAndView().getViewName();
        if (viewName != null && viewName.indexOf("/") != -1) {
            try {
                Integer integer = Integer.valueOf(viewName.substring(viewName.lastIndexOf("/") + 1));

                this.mockMvc
                        .perform(
                                get(_PANEL_URL + "/trash/" + integer)
                                        .session(session)
                                        .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.status").value("ok"))
                        .andExpect(jsonPath("$.messages", Matchers.hasSize(1)))
                        .andExpect(jsonPath("$.result", Matchers.anEmptyMap()))
                ;

            } catch (Exception x) {
            }
        }
    }

    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pListOnlineTest  #
    //============================= pListOnlineTest_accessTo  #
    @Test
    public void pListOnlineTest_accessTo() throws Exception {
        //----------------------- Guest
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/list/online")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/list/online")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/list/online")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_user_listOnline.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("searchId", "cols"))
        ;

    }
    //............................. pListOnlineTest_accessTo  #

    @Test
    public void pListOnlineTest_post_ajax_submit() throws Exception {

        //----------------------- Check Access
        this.mockMvc
                .perform(
                        post(_PANEL_URL + "/list/online")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("ap", MockObjectInstances.getInstance().getListAP())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("nok"))
                .andExpect(jsonPath("$.messages", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.result", Matchers.anEmptyMap()))
        ;

        //----------------------- Success List LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        post(_PANEL_URL + "/list/online")
                                .session(session)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("ap", MockObjectInstances.getInstance().getListAP())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("nok"))
                .andExpect(jsonPath("$.messages", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.result", Matchers.anEmptyMap()))
        ;

        //----------------------- Success List Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        post(_PANEL_URL + "/list/online")
                                .session(session)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("ap", MockObjectInstances.getInstance().getListAP())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("ok"))
                .andExpect(jsonPath("$.result").isNotEmpty())
        ;

    }

    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pListInactiveTest  #
    //============================= pListInactiveTest_accessTo  #
    @Test
    public void pListInactiveTest_accessTo() throws Exception {
        //----------------------- Guest
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/list/inactive")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/list/inactive")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/list/inactive")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_user_listInactive.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("searchId", "cols"))
        ;

    }
    //............................. pListInactiveTest_accessTo  #

    @Test
    public void pListInactiveTest_post_ajax_submit() throws Exception {

        //----------------------- Check Access
        this.mockMvc
                .perform(
                        post(_PANEL_URL + "/list/inactive")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("ap", MockObjectInstances.getInstance().getListAP())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("nok"))
                .andExpect(jsonPath("$.messages", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.result", Matchers.anEmptyMap()))
        ;

        //----------------------- Success List LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        post(_PANEL_URL + "/list/inactive")
                                .session(session)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("ap", MockObjectInstances.getInstance().getListAP())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("nok"))
                .andExpect(jsonPath("$.messages", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.result", Matchers.anEmptyMap()))
        ;

        //----------------------- Success List Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        post(_PANEL_URL + "/list/inactive")
                                .session(session)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("ap", MockObjectInstances.getInstance().getListAP())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("ok"))
                .andExpect(jsonPath("$.result").isNotEmpty())
        ;

    }

    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pAccessListTest  #
    //============================= pAccessListTest_accessTo  #
    @Test
    public void pAccessListTest_accessTo() throws Exception {
        //----------------------- Guest
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/access/list/0")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/access/list/0")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/access/list/0")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(_PANEL_URL + "/list"))
                .andExpect(model().hasNoErrors())
        ;

    }
    //............................. pAccessListTest_accessTo  #

    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# fSigninTest  #
    //============================= fSigninTest_accessTo  #
    @Test
    public void fSigninTest_accessTo() throws Exception {
        //----------------------- Guest
        this.mockMvc
                .perform(
                        get("/signin")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_user_signin.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        //----------------------- Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get("/signin")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }
    //............................. fSigninTest_accessTo  #

    @Test
    public void fSigninTest_post_success() throws Exception {

        MvcResult mvcResult = this.mockMvc
                .perform(
                        post("/signin")
                                .param("username", "admin")
                                .param("password", "admin")
                                .param("force", "false")
                                .param("captchaCode", "")
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/panel"))
                .andReturn();

        Assert.assertNotNull(mvcResult.getRequest().getSession().getAttribute("sUser"));

    }

    @Test
    public void fSigninTest_post_successWithForce() throws Exception {

        this.mockMvc
                .perform(
                        post("/signin")
                                .param("username", "admin")
                                .param("password", "admin")
                                .param("force", "true")
                                .param("captchaCode", "")
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/panel"))
        ;
    }

    @Test
    public void fSigninTest_post_ifSignedInPrev() throws Exception {

        this.signinAsAdmin();

        this.mockMvc
                .perform(
                        post("/signin")
                                .param("username", "admin")
                                .param("password", "admin")
                                .param("force", "false")
                                .param("captchaCode", "")
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/panel"))
        ;
    }

    @Test
    public void fSigninTest_post_failed() throws Exception {

        MvcResult mvcResult = this.mockMvc
                .perform(
                        post("/signin")
                                .param("username", "1212")
                                .param("password", "q23eewrwer")
                                .param("force", "false")
                                .param("captchaCode", "")
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/signin"))
                .andExpect(flash().attributeExists("notice"))
                .andReturn();

        Assert.assertNull(mvcResult.getRequest().getSession().getAttribute("sUser"));

    }

    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# fSignoutTest  #
    @Test
    public void fSignoutTest_accessTo() throws Exception {

        //----------------------- Admin
        this.signinAsAdmin();

        MvcResult mvcResult = this.mockMvc
                .perform(
                        get("/signout")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andReturn();
        Assert.assertNull(mvcResult.getRequest().getSession().getAttribute("sUser"));
    }


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pChangeYourPasswordTest  #
    //============================= pChangeYourPasswordTest_accessTo  #
    @Test
    public void pChangeYourPasswordTest_accessTo() throws Exception {
        //----------------------- Guest

        //----------------------- LogManager

        //----------------------- Admin

    }
    //............................. pChangeYourPasswordTest_accessTo  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pRandomUsernameTest  #

    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pDeactivateUserTest  #
    //============================= pDeactivateUserTest_accessTo  #
    @Test
    public void pDeactivateUserTest_accessTo() throws Exception {
        //----------------------- Guest

        //----------------------- LogManager

        //----------------------- Admin

    }
    //............................. pDeactivateUserTest_accessTo  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pExpireTest  #
    //============================= pExpireTest_accessTo  #
    @Test
    public void pExpireTest_accessTo() throws Exception {
        //----------------------- Guest

        //----------------------- LogManager

        //----------------------- Admin

    }
    //............................. pExpireTest_accessTo  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pAccessTest  #
    //============================= pAccessTest_accessTo  #
    @Test
    public void pAccessTest_accessTo() throws Exception {
        //----------------------- Guest

        //----------------------- LogManager

        //----------------------- Admin

    }
    //............................. pAccessTest_accessTo  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# fSignOutTest  #
    //============================= fSignOutTest_accessTo  #
    @Test
    public void fSignOutTest_accessTo() throws Exception {
        //----------------------- Guest

        //----------------------- LogManager

        //----------------------- Admin

    }
    //............................. fSignOutTest_accessTo  #


    //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pReSigninTest  #


}
