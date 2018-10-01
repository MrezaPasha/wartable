package org.sadr.web.main.note.note;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author masoud
 */
@Repository
@Component
public class NoteDaoImp extends GenericDaoImpl<Note> implements NoteDao {
}
