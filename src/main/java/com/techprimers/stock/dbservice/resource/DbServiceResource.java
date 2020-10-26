package com.techprimers.stock.dbservice.resource;

import com.techprimers.stock.dbservice.model.Quote;
import com.techprimers.stock.dbservice.model.Quotes;
import com.techprimers.stock.dbservice.repository.QuotesRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EnableJpaRepositories(basePackages = "com.techprimers.stock.dbservice.repository")
@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {
    private QuotesRepository quotesRepository;


    public DbServiceResource(QuotesRepository quotesRepositry) {
        this.quotesRepository = quotesRepositry;
    }
    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username){
              return getQuotesByUserName(username);
    }

    @PostMapping("/add")
    public List <String> add (@RequestBody final Quotes quotes){
       quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUserName(),quote)).forEach(quote ->{quotesRepository.save(quote);
       });
        return getQuotesByUserName(quotes.getUserName());
    }

    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String username) {

        List<Quote> quotes = quotesRepository.findByUserName(username);
        quotes.stream().forEach(quote -> quotesRepository.delete(quote));

        return getQuotesByUserName(username);
    }


    private List<String> getQuotesByUserName(@PathVariable("username") String username) {
        return quotesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

}
