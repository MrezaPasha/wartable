package org.sadr.web.main.note.note;

import org.sadr.web.config.Config;
import org.sadr.web.main.system.irror.IrrorConfig;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class NoteConfig extends Config {

    @Bean
    public NoteDaoImp noteDaoImp() {
        NoteDaoImp udi = new NoteDaoImp();
        return udi;
    }

    @Bean
    public NoteServiceImp noteServiceImp() {
        NoteServiceImp usi = new NoteServiceImp();
        usi.setDao(noteDaoImp());
        return usi;
    }

    @Bean
    public NoteController noteController() {
        NoteController uc = new NoteController();
        uc.setService(noteServiceImp());
        uc.setIrrorService(new IrrorConfig().irrorServiceImp());
        return uc;
    }
}
