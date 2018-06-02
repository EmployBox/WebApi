package isel.ps.employbox.services.curricula;

import com.github.jayield.rapper.DataRepository;
import com.github.jayield.rapper.utils.Pair;
import isel.ps.employbox.ErrorMessages;
import isel.ps.employbox.exceptions.BadRequestException;
import isel.ps.employbox.exceptions.ConflictException;
import isel.ps.employbox.model.entities.CurriculumChilds.PreviousJobs;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class PreviousJobService {
    private final DataRepository<PreviousJobs, Long> previousJobsRepo;
    private final CurriculumService curriculumService;

    public PreviousJobService(DataRepository<PreviousJobs, Long> previousJobsRepo, CurriculumService curriculumService) {
        this.previousJobsRepo = previousJobsRepo;
        this.curriculumService = curriculumService;
    }

    public CompletableFuture<Stream<PreviousJobs>> getCurriculumPreviousJobs(long curriculumId){
        return previousJobsRepo.findWhere(new Pair<>("curriculumId",curriculumId))
                .thenApply(Collection::stream);
    }


    public CompletableFuture<PreviousJobs> addPreviousJobToCurriculum (
            long accountId,
            long curriculumId,
            PreviousJobs previousJobs,
            String email
    ) {
        if(previousJobs.getAccountId() != accountId || previousJobs.getCurriculumId() != curriculumId)
            throw new ConflictException(ErrorMessages.BAD_REQUEST_IDS_MISMATCH);
        return curriculumService.getCurriculum(accountId, curriculumId,email)
                .thenCompose(curriculum -> previousJobsRepo.create( previousJobs))
                .thenApply(aVoid -> previousJobs);
    }


    public Mono<Void> updatePreviousJob(
            long pvjId,
            long accountId,
            long curriculumId,
            PreviousJobs previousJobs,
            String email
    ) {
        if(previousJobs.getAccountId() != pvjId)
            throw new BadRequestException(ErrorMessages.BAD_REQUEST_IDS_MISMATCH);
        return Mono.fromFuture(curriculumService.getCurriculum(accountId, curriculumId, email)
                .thenCompose(curriculum -> previousJobsRepo.update(previousJobs))
        );
    }

    public Mono<Void> deletePreviousJob(
            long previousJobId,
            long accountId,
            long curriculumId,
            String email
    ) {
        return Mono.fromFuture(
                curriculumService.getCurriculum(accountId, curriculumId, email)
                        .thenCompose(curriculum -> previousJobsRepo.deleteById(previousJobId))
        );
    }

}