package org.sadr.web.main.note.note;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class NoteServiceImp extends GenericServiceImpl<Note, NoteDao> implements NoteService {
}
