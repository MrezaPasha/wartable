package org.sadr.share.main.pollVotes;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PollVotesServiceImp extends GenericServiceImpl<PollVotes, PollVotesDao> implements PollVotesService {
}
