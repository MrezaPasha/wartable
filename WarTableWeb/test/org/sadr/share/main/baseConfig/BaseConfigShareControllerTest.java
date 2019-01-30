package org.sadr.share.main.baseConfig;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sadr.config.AbstractContextControllerTests;
import org.sadr.utils.MockObjectBuilder;
import org.sadr.utils.MockObjectInstances;
import org.sadr.web.main._core._type.TtTile___;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class BaseConfigShareControllerTest extends AbstractContextControllerTests {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/baseConfig";


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
                .andExpect(view().name(TtTile___.p_service_baseConfig_list.getCode()))
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

        BaseConfig realBaseConfig = MockObjectInstances.getInstance().getRealBaseConfig();

        //----------------------- LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/edit/" + realBaseConfig.getId())
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_e_401.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeDoesNotExist("baseConfig", "action"))
        ;

        //----------------------- Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/edit/" + realBaseConfig.getId())
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_service_baseConfig_edit.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("baseConfig", "action"))
        ;
    }
    //............................. pEditTest_accessTo  #

    //============================= pEditTest_post_submit  #
    @Test
    public void pEditTest_post_submit() throws Exception {

        //----------------------- Check Access
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/edit", MockObjectInstances.getInstance().getBaseConfig(true))
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
        ;

        BaseConfig realBaseConfig = MockObjectInstances.getInstance().getRealBaseConfig();
        this.signinAsAdmin();

        //----------------------- Success Edit
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/edit", realBaseConfig)
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern(_PANEL_URL + "/edit/*"))
                .andExpect(flash().attributeExists("notice"))
        ;

        //----------------------- Not Found  BaseConfig 
        this.mockMvc
                .perform(
                        MockObjectBuilder
                                .postForm(_PANEL_URL + "/edit", MockObjectInstances.getInstance().getBaseConfig(true))
                                .session(session)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("notice", "baseConfig"))
        ;
    }
    //............................. pEditTest_post_submit  #

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

}
