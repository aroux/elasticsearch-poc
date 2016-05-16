package org.github.aroux.elasticsearchpoc.engine;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.github.aroux.elasticsearchpoc.entity.*;
import org.github.aroux.elasticsearchpoc.repository.DomainRepository;
import org.github.aroux.elasticsearchpoc.repository.LKPRepository;
import org.github.aroux.elasticsearchpoc.repository.ViewRepository;
import org.github.aroux.elasticsearchpoc.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MyEngine {

    private static final Logger logger = LoggerFactory.getLogger(MyEngine.class);

    private final ElasticsearchTemplate elasticsearchTemplate;

    private final DomainRepository domainRepository;
    private final ViewRepository viewRepository;
    private final LKPRepository lkpRepository;

    private final SearchService searchService;

    @Autowired
    public MyEngine(ElasticsearchTemplate elasticsearchTemplate, DomainRepository domainRepository, ViewRepository viewRepository, LKPRepository lkpRepository, SearchService searchService) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.domainRepository = domainRepository;
        this.viewRepository = viewRepository;
        this.lkpRepository = lkpRepository;
        this.searchService = searchService;
    }


    public void goDomain() throws InterruptedException {
        domainRepository.deleteAll();
        viewRepository.deleteAll();
        lkpRepository.deleteAll();

        List<Domain> domains = generateDomains();
        domainRepository.save(domains);

        List<View> views = generateViews();
        viewRepository.save(views);

        List<LKP> lkps = generateLKPs();
        lkpRepository.save(lkps);

//        Thread.sleep(1000);
//
//        String search = "suisse";
//        Page<Domain> domainsPage = domainRepository.findByNameOrDescription(
//                search, search, new PageRequest(0,10));
//        logger.info("Found domain by name: " + domainsPage.getNumberOfElements());
//        domainsPage.forEach(x -> logger.info(x.toString()));
    }


    private List<Domain> generateDomains() {
        Domain domain1 = new Domain();
        domain1.setName("Domain_1_name");
        domain1.setDescription("La Suisse n'a plus le choix et doit battre la Suède");

        Domain domain2 = new Domain();
        domain2.setName("Domain_2_name");
        domain2.setDescription("Shia LaBeouf va incarner John McEnroe au cinéma");

        return Arrays.asList(domain1, domain2);
    }

    private List<View> generateViews() {
        View view11 = new View();
        view11.setName("View_1_1_name");
        view11.setDescription("Il était venu chercher sa femme au travail");
        View view12 = new View();
        view12.setName("View_1_2_name");
        view12.setDescription("Une Ferrari F550 Maranello fait une violente embardée");
        return Arrays.asList(view11, view12);
    }


    private List<LKP> generateLKPs() {
        LKP lkp11 = new LKP();
        lkp11.setDomain("Domain_1_name");
        lkp11.setName("LKP_1_1_name");
        lkp11.setDescription("Denis Baupin aurait tenté d'étouffer l'enquête");
        LKPValue lkpValue111 = new LKPValue();
        lkpValue111.setKey("LKPValue_1_1_1_key");
        lkpValue111.setValue("LKPValue_1_1_1_value");
        LKPValue lkpValue112 = new LKPValue();
        lkpValue112.setKey("LKPValue_1_1_2_key");
        lkpValue112.setValue("LKPValue_1_1_2_value");
        LKPValue lkpValue113 = new LKPValue();
        lkpValue113.setKey("LKPValue_1_1_3_key");
        lkpValue113.setValue("LKPValue_1_1_3_value");
        LKPValue lkpValue114 = new LKPValue();
        lkpValue114.setKey("LKPValue_1_1_4_key");
        lkpValue114.setValue("LKPValue_1_1_4_value");
        lkp11.setValues(Arrays.asList(lkpValue111, lkpValue112, lkpValue113, lkpValue114));

        LKP lkp12 = new LKP();
        lkp12.setName("LKP_1_2_name");
        lkp12.setDomain("Domain_1_name");
        lkp12.setDescription("Les voyageurs ont dû s'armer de patience");

        LKP lkp21 = new LKP();
        lkp21.setName("LKP_2_1_name");
        lkp21.setDomain("Domain_2_name");
        lkp21.setDescription("Il commence sa carrière en Serie A à 39 ans");

        return Arrays.asList(lkp11, lkp12, lkp21);
    }

    public List<Domain> generateRandomDomains() {
        EnhancedRandom enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .seed(123L)
                .build();

        return Stream.generate(() -> enhancedRandom.nextObject(Domain.class))
                .limit(20).collect(Collectors.toList());
    }

}
