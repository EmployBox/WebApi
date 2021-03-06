package isel.ps.employbox.model.binders;

import isel.ps.employbox.model.entities.Rating;
import isel.ps.employbox.model.input.InRating;
import isel.ps.employbox.model.output.OutRating;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class RatingBinder implements ModelBinder<Rating,OutRating,InRating> {

    @Override
    public CompletableFuture<OutRating> bindOutput(Rating rating) {
        return CompletableFuture.completedFuture(new OutRating(
                rating.getAccountIdFrom(),
                rating.getAccountIdTo(),
                rating.getWorkLoad(),
                rating.getWage(),
                rating.getWorkEnviroment(),
                rating.getCompetence(),
                rating.getPonctuality(),
                rating.getAssiduity(),
                rating.getDemeanor(),
                rating.getVersion()
        ));
    }

    @Override
    public Rating bindInput(InRating object) {
        return new Rating(
                object.getAccountIdFrom(),
                object.getAccountIdTo(),
                object.getWorkLoad(),
                object.getWage(),
                object.getWorkEnvironment(),
                object.getCompetence(),
                object.getPontuality(),
                object.getAssiduity(),
                object.getDemeanor(),
                object.getVersion());
    }
}
