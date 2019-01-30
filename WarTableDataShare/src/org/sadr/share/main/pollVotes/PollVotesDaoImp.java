package org.sadr.share.main.pollVotes;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class PollVotesDaoImp extends GenericDaoImpl<PollVotes> implements PollVotesDao {
}
