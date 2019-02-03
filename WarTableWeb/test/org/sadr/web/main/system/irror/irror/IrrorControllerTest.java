package org.sadr.web.main.system.irror.irror;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sadr._core.utils.Digester;
import org.sadr.config.AbstractContextControllerTests;
import org.sadr.utils.MockObjectBuilder;
import org.sadr.utils.MockObjectInstances;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main.admin.user.user.User;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class IrrorControllerTest extends AbstractContextControllerTests {

 ////////////////////
 private final String _PANEL_URL = "/panel/irror";


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
                .andExpect(view().name(TtTile___.p_e_list.getCode()))
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


 //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# pDetailsTest  #
    //============================= pDetailsTest_accessTo  #
     @Test
    public void pDetailsTest_accessTo() throws Exception {
        //----------------------- Guest
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/details/0")
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.f_e_401.getCode()))
                .andExpect(model().hasNoErrors())
        ;

        Irror realIrror = MockObjectInstances.getInstance().getRealIrror();

        //----------------------- LogManager
        this.signinAsLogManager();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/details/" + realIrror.getId())
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_e_401.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeDoesNotExist("irror"))
        ;

        //----------------------- Admin
        this.signinAsAdmin();
        this.mockMvc
                .perform(
                        get(_PANEL_URL + "/details/" + realIrror.getId())
                                .session(session)
                                .accept(MediaType.TEXT_HTML)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(TtTile___.p_e_details.getCode()))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("irror"))
        ;
    }
    //............................. pDetailsTest_accessTo  #


 //#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=#=##=# gServletErrorHandlerTest  #
    //============================= gServletErrorHandlerTest_accessTo  #
@Test
    public void gServletErrorHandlerTest_accessTo() throws Exception {
        //--- EMPTY_TEST 

    }
   //............................. gServletErrorHandlerTest_accessTo  #

}
