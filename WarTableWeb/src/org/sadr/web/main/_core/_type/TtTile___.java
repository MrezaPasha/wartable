package org.sadr.web.main._core._type;

import org.sadr._core.utils.Environment;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author masoud
 */
public enum TtTile___ {

    ///============== A
    a_archive_file_cred(TtSystemTileLayout.Panel, null),
    a_user_cred(TtSystemTileLayout.Panel, null),
    a_note_cred(TtSystemTileLayout.Panel, null),
    a_service_startupNotice_cred(TtSystemTileLayout.Panel, null),

    ///============== A Share
    a_service_baseConfig_cred(TtSystemTileLayout.Panel, null),
    a_service_baseErrors_cred(TtSystemTileLayout.Panel, null),
    a_service_grade_cred(TtSystemTileLayout.Panel, null),
//    a_service_layer_cred(TtSystemTileLayout.Panel, null),
    a_service_map_cred(TtSystemTileLayout.Panel, null),
    a_service_map_room_cred(TtSystemTileLayout.Panel, null),
    a_service_meeting_cred(TtSystemTileLayout.Panel, null),
    a_service_object_cred(TtSystemTileLayout.Panel, null),
    a_service_orgPosition_cred(TtSystemTileLayout.Panel, null),
    a_service_room_cred(TtSystemTileLayout.Panel, null),
    a_service_room_map_cred(TtSystemTileLayout.Panel, null),
    a_service_room_user_cred(TtSystemTileLayout.Panel, null),
    a_service_room_ServiceUser_cred(TtSystemTileLayout.Panel, null),
    a_service_room_ServiceUser_credR(TtSystemTileLayout.Panel, null),
    a_service_serviceUser_cred(TtSystemTileLayout.Panel, null),
    a_service_personModel_cred(TtSystemTileLayout.Panel, null),


    ///============== F

    f_develop_developing(TtSystemTileLayout.Front, null),
    f_index(TtSystemTileLayout.Front, null),
    f_e_400(TtSystemTileLayout.Front, null),
    f_e_401(TtSystemTileLayout.Front, null),
    f_e_403(TtSystemTileLayout.Front, null),
    f_e_404(TtSystemTileLayout.Front, null),
    f_e_405(TtSystemTileLayout.Front, null),
    f_e_413(TtSystemTileLayout.Front, null),
    f_e_414(TtSystemTileLayout.Front, null),
    f_e_444(TtSystemTileLayout.Front, null),
    f_e_495(TtSystemTileLayout.Front, null),
    f_e_500(TtSystemTileLayout.Front, null),
    f_e_503(TtSystemTileLayout.Front, null),
    f_user_signin(TtSystemTileLayout.Front, null),
    ///============== Panel
    p_archive_file_create(TtSystemTileLayout.Panel, a_archive_file_cred),
    p_archive_file_edit(TtSystemTileLayout.Panel, a_archive_file_cred),
    p_archive_file_details(TtSystemTileLayout.Panel, null),
    p_archive_file_list(TtSystemTileLayout.Panel, null),
    p_backup_list(TtSystemTileLayout.Panel, null),
    p_backup_upload(TtSystemTileLayout.Panel, null),
    p_develop_index(TtSystemTileLayout.Panel, null),
    p_e_400(TtSystemTileLayout.Panel, null),
    p_e_401(TtSystemTileLayout.Panel, null),
    p_e_403(TtSystemTileLayout.Panel, null),
    p_e_404(TtSystemTileLayout.Panel, null),
    p_e_405(TtSystemTileLayout.Panel, null),
    p_e_413(TtSystemTileLayout.Panel, null),
    p_e_414(TtSystemTileLayout.Panel, null),
    p_e_444(TtSystemTileLayout.Panel, null),
    p_e_495(TtSystemTileLayout.Panel, null),
    p_e_500(TtSystemTileLayout.Panel, null),
    p_e_503(TtSystemTileLayout.Panel, null),
    p_e_list(TtSystemTileLayout.Panel, null),
    p_e_details(TtSystemTileLayout.Panel, null),
    p_dashboard(TtSystemTileLayout.Panel, null),
    p_setting_index(TtSystemTileLayout.Panel, null),
    p_setting_init(TtSystemTileLayout.Panel, null),
    p_note_create(TtSystemTileLayout.Panel, a_note_cred),
    p_note_edit(TtSystemTileLayout.Panel, a_note_cred),
    p_note_details(TtSystemTileLayout.Panel, null),
    p_note_list(TtSystemTileLayout.Panel, null),
    p_propertor_backup(TtSystemTileLayout.Panel, null),
    p_propertor_boot(TtSystemTileLayout.Panel, null),
    p_propertor_control(TtSystemTileLayout.Panel, null),
    p_propertor_log(TtSystemTileLayout.Panel, null),
    p_propertor_web(TtSystemTileLayout.Panel, null),
    p_setting_site(TtSystemTileLayout.Panel, null),
    p_sys_log_visit(TtSystemTileLayout.Panel, null),
    p_sys_log_list(TtSystemTileLayout.Panel, null),
    p_sys_log_details(TtSystemTileLayout.Panel, null),
    p_sys_log_remote_list(TtSystemTileLayout.Panel, null),
    p_sys_log_remote_details(TtSystemTileLayout.Panel, null),
    p_sys_log_remote_moduleList(TtSystemTileLayout.Panel, null),
    p_sys_log_remote_taskList(TtSystemTileLayout.Panel, null),
    p_sys_log_remote_taskConfig(TtSystemTileLayout.Panel, null),
    p_sys_log_remote_onlineLogging(TtSystemTileLayout.Panel, null),
    p_sys_log_signin_list(TtSystemTileLayout.Panel, null),
    p_sys_model_field_details(TtSystemTileLayout.Panel, null),
    p_sys_model_field_list(TtSystemTileLayout.Panel, null),
    p_sys_model_list(TtSystemTileLayout.Panel, null),
    p_sys_module_list(TtSystemTileLayout.Panel, null),
    p_sys_module_listConfirm(TtSystemTileLayout.Panel, null),
    p_sys_registery_edit(TtSystemTileLayout.Panel, null),
    p_sys_registery_list(TtSystemTileLayout.Panel, null),
    p_sys_task_active(TtSystemTileLayout.Panel, null),
    p_sys_task_confirm(TtSystemTileLayout.Panel, null),
    p_uiBag_set(TtSystemTileLayout.Panel, null),

    p_user_porter_details(TtSystemTileLayout.Panel, null),
    p_user_porter_list(TtSystemTileLayout.Panel, null),
    p_user_access_assign(TtSystemTileLayout.Panel, null),
    p_user_access_list(TtSystemTileLayout.Panel, null),
    p_user_create(TtSystemTileLayout.Panel, a_user_cred),
    p_user_changeUserPass(TtSystemTileLayout.Panel, null),
    p_user_changeYourPass(TtSystemTileLayout.Panel, null),
    p_user_edit(TtSystemTileLayout.Panel, a_user_cred),
    p_user_yourEdit(TtSystemTileLayout.Panel, null),
    p_user_group_access_assign(TtSystemTileLayout.Panel, null),
    p_user_group_access_list(TtSystemTileLayout.Panel, null),
    p_user_group_assign(TtSystemTileLayout.Panel, null),
    p_user_group_desk(TtSystemTileLayout.Panel, null),
    p_user_list(TtSystemTileLayout.Panel, null),
    p_user_listInactive(TtSystemTileLayout.Panel, null),
    p_user_listOnline(TtSystemTileLayout.Panel, null),
    p_user_profile(TtSystemTileLayout.Panel, null),
    p_user_reSignin(TtSystemTileLayout.Panel, null),

    ///============== Panel Share
    p_service_baseConfig_edit(TtSystemTileLayout.Panel, a_service_baseConfig_cred),
    p_service_baseConfig_list(TtSystemTileLayout.Panel, null),
    p_service_baseErrors_create(TtSystemTileLayout.Panel, a_service_baseErrors_cred),
    p_service_baseErrors_edit(TtSystemTileLayout.Panel, a_service_baseErrors_cred),
    p_service_baseErrors_list(TtSystemTileLayout.Panel, null),
    p_service_criticalLog_details(TtSystemTileLayout.Panel, null),
    p_service_criticalLog_list(TtSystemTileLayout.Panel, null),
    p_service_grade_create(TtSystemTileLayout.Panel, a_service_grade_cred),
    p_service_grade_edit(TtSystemTileLayout.Panel, a_service_grade_cred),
    p_service_grade_list(TtSystemTileLayout.Panel, null),
//    p_service_layer_create(TtSystemTileLayout.Panel, a_service_layer_cred),
//    p_service_layer_edit(TtSystemTileLayout.Panel, a_service_layer_cred),
//    p_service_layer_list(TtSystemTileLayout.Panel, null),
    p_service_map_create(TtSystemTileLayout.Panel, a_service_map_cred),
    p_service_map_edit(TtSystemTileLayout.Panel, a_service_map_cred),
    p_service_map_list(TtSystemTileLayout.Panel, null),
    p_service_map_room_create(TtSystemTileLayout.Panel, a_service_map_room_cred),
    p_service_map_room_edit(TtSystemTileLayout.Panel, a_service_map_room_cred),
    p_service_map_room_list(TtSystemTileLayout.Panel, null),
    p_service_map_layer_list(TtSystemTileLayout.Panel, null),
    p_service_meeting_edit(TtSystemTileLayout.Panel, a_service_meeting_cred),
    p_service_meeting_details(TtSystemTileLayout.Panel, null),
    p_service_meeting_list(TtSystemTileLayout.Panel, null),
    p_service_meeting_talk_list(TtSystemTileLayout.Panel, null),
    p_service_meeting_talk_details(TtSystemTileLayout.Panel, null),
    p_service_meeting_setting_list(TtSystemTileLayout.Panel, null),
    p_service_meeting_setting_details(TtSystemTileLayout.Panel, null),
    p_service_meeting_vector_details(TtSystemTileLayout.Panel, null),
    p_service_meeting_vector_add(TtSystemTileLayout.Panel, null),
    p_service_meeting_vector_list(TtSystemTileLayout.Panel, null),
    p_service_object_create(TtSystemTileLayout.Panel, a_service_object_cred),
    p_service_object_edit(TtSystemTileLayout.Panel, a_service_object_cred),
    p_service_object_list(TtSystemTileLayout.Panel, null),
    p_service_object_preview(TtSystemTileLayout.Panel, null),
    p_service_orgPosition_create(TtSystemTileLayout.Panel, a_service_orgPosition_cred),
    p_service_orgPosition_edit(TtSystemTileLayout.Panel, a_service_orgPosition_cred),
    p_service_orgPosition_list(TtSystemTileLayout.Panel, null),
    p_service_personModel_create(TtSystemTileLayout.Panel, a_service_personModel_cred),
    p_service_personModel_edit(TtSystemTileLayout.Panel, a_service_personModel_cred),
    p_service_personModel_list(TtSystemTileLayout.Panel, null),

    p_service_room_create(TtSystemTileLayout.Panel, a_service_room_cred),
    p_service_room_edit(TtSystemTileLayout.Panel, a_service_room_cred),
    p_service_room_list(TtSystemTileLayout.Panel, null),
    p_service_room_map_create(TtSystemTileLayout.Panel, a_service_room_map_cred),
    p_service_room_map_edit(TtSystemTileLayout.Panel, a_service_room_map_cred),
    p_service_room_map_list(TtSystemTileLayout.Panel, null),
    p_service_room_chat_list(TtSystemTileLayout.Panel, null),
    p_service_room_chat_details(TtSystemTileLayout.Panel, null),
    p_service_room_chat_conversation(TtSystemTileLayout.Panel, null),
    p_service_room_user_create(TtSystemTileLayout.Panel, a_service_room_user_cred),
    p_service_room_user_edit(TtSystemTileLayout.Panel, a_service_room_user_cred),
    p_service_room_user_details(TtSystemTileLayout.Panel, null),
    p_service_room_user_list(TtSystemTileLayout.Panel, null),
    //    p_service_room_ServiceUser_details(TtSystemTileLayout.Panel, null),
//    p_service_room_ServiceUser_create(TtSystemTileLayout.Panel, a_service_room_ServiceUser_cred),
//    p_service_room_ServiceUser_edit(TtSystemTileLayout.Panel, a_service_room_ServiceUser_cred),
//    p_service_room_ServiceUser_list(TtSystemTileLayout.Panel, null),
    p_service_serviceLog_details(TtSystemTileLayout.Panel, null),
    p_service_serviceLog_list(TtSystemTileLayout.Panel, null),
    p_service_serviceUser_create(TtSystemTileLayout.Panel, a_service_serviceUser_cred),
    p_service_serviceUser_edit(TtSystemTileLayout.Panel, a_service_serviceUser_cred),
    p_service_serviceUser_list(TtSystemTileLayout.Panel, null),
    p_service_serviceUser_changeUserPass(TtSystemTileLayout.Panel, null),
    p_service_serviceUser_listOnline(TtSystemTileLayout.Panel, null),
    p_service_serviceUser_room(TtSystemTileLayout.Panel, null),
    p_service_serviceUser_chat_details(TtSystemTileLayout.Panel, null),
    p_service_serviceUser_chat_conversation(TtSystemTileLayout.Panel, null),
    p_service_startupNotice_edit(TtSystemTileLayout.Panel, a_service_startupNotice_cred),
    p_service_startupNotice_create(TtSystemTileLayout.Panel, a_service_startupNotice_cred),
    p_service_startupNotice_details(TtSystemTileLayout.Panel, null),
    p_service_startupNotice_list(TtSystemTileLayout.Panel, null),
    p_service_startupNotice_item_list(TtSystemTileLayout.Panel, null),

    ;
    private final TtTile___ alternative;// alternative codes link the page of a TtTile to another file. sharing jsp files
    private final TtSystemTileLayout template;

    TtTile___(TtSystemTileLayout template, TtTile___ alternative) {
        this.alternative = alternative;
        this.template = template;
    }

    public TtSystemTileLayout getTemplate() {
        return template;
    }

    public ModelAndView ___getDisModel() {
        return new ModelAndView(this.getCode());
    }

    public ModelAndView ___getDisModel(TtTaskActionSubType subType, TtTaskActionStatus actionStatus) {
        return new ModelAndView(this.getCode())
                .addObject("actionStatus", actionStatus)
                .addObject("actionSubType", subType);
    }

    public ModelAndView ___getDisModel(String action) {
        return new ModelAndView(this.getCode())
                .addObject("action", Environment.getInstance().getContextPath() + action);
    }

    public ModelAndView ___getDisModel(String action, TtTaskActionSubType subType, TtTaskActionStatus actionStatus) {
        return new ModelAndView(this.getCode())
                .addObject("action", Environment.getInstance().getContextPath() + action)
                .addObject("actionStatus", actionStatus)
                .addObject("actionSubType", subType);
    }

    public String getCode() {
        return this.toString().replace("_", ".");
    }

    public String getMessageCode() {
        return "T." + this.toString().replace("_", ".");
    }

    public String getFilePath() {
        return getFolderPath() + Environment.FILE_SEPARATOR + getFileName();
    }

    public String getFolderPath() {
        String s;
        if (this.getAlternative() == null) {
            s = this.toString();
        } else {
            s = this.getAlternative().toString();
        }
        s = s.substring(s.indexOf("_") + 1);
        try {
            return Environment.FILE_SEPARATOR + this.template.getFolder() + Environment.FILE_SEPARATOR + s.substring(0, s.lastIndexOf("_")).replaceAll("_", Environment.FILE_SEPARATOR);
        } catch (Exception e) {
            return Environment.FILE_SEPARATOR + this.template.getFolder() + Environment.FILE_SEPARATOR + s.replaceAll("_", Environment.FILE_SEPARATOR);
        }
    }

    public String getFileName() {
        String r = "", s;
        if (this.getAlternative() == null) {
            s = this.toString();
        } else {
            s = this.getAlternative().toString();
        }
        for (String c : s.substring(s.lastIndexOf("_") + 1).split("")) {
            if (c.toLowerCase() != c) {
                r += "-" + c.toLowerCase();
            } else {
                r += c;
            }
        }
        return r + ".jsp";
    }

    public TtTile___ getAlternative() {
        return alternative;
    }
}
