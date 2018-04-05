package isel.ps.employbox.controllers;

import isel.ps.employbox.exceptions.BadRequestException;
import isel.ps.employbox.model.binder.ModelBinder;
import isel.ps.employbox.model.input.InRating;
import isel.ps.employbox.model.output.OutRating;
import isel.ps.employbox.services.RatingService;
import isel.ps.employbox.model.entities.Rating;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH;

@RestController
@RequestMapping("/accounts/{id}/ratings")
public class RatingController {

    private final ModelBinder<Rating, OutRating, InRating, String> ratingBinder;
    private final RatingService ratingService;

    public RatingController(ModelBinder<Rating, OutRating, InRating, String> ratingBinder, RatingService ratingService) {
        this.ratingBinder = ratingBinder;
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<OutRating> getRatings(@PathVariable long id, @RequestParam String type){
        if(type.equals("done") || type.equals("received"))
            return ratingBinder.bindOutput(ratingService.getRatings(id, type));
        else
            throw new BadRequestException("Type must be either \"done\" or \"received\"");
    }

    @GetMapping
    public Optional<OutRating> getRating(@PathVariable long id, @RequestParam long accountTo){
        return ratingService.getRating(id, accountTo)
                .map(ratingBinder::bindOutput);
    }

    @PutMapping
    public void updateRating(@PathVariable long id, @RequestParam long accountTo, @RequestBody InRating rating){
        if(id != rating.getAccountIDFrom() || accountTo != rating.getAccountIDTo()) throw new BadRequestException(BAD_REQUEST_IDS_MISMATCH);
        ratingService.updateRating(ratingBinder.bindInput(rating));
    }

    @PostMapping
    public void createRating(@PathVariable long id, @RequestParam long accountTo, @RequestBody InRating rating){
        if(id != rating.getAccountIDFrom() || accountTo != rating.getAccountIDTo()) throw new BadRequestException(BAD_REQUEST_IDS_MISMATCH);
        ratingService.createRating(ratingBinder.bindInput(rating));
    }

    @DeleteMapping
    public void deleteRating(@PathVariable long id, @RequestParam long accountTo){
        ratingService.deleteRating(id, accountTo);
    }
}
