package edu.marius.graph.app;

import edu.marius.graph.services.WebCrawlerService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import edu.marius.graph.domain.cv.Cv;
import edu.marius.graph.entity.CvType;
import edu.marius.graph.entity.JobPosting;
import edu.marius.graph.entity.JobSummary;
import edu.marius.graph.entity.JobType;
import edu.marius.graph.entity.UserType;
import edu.marius.graph.mappers.cv.CvMapper;
import edu.marius.graph.mappers.cv.JobMapper;
import edu.marius.graph.mappers.job.JobDescriptionMapper;
import edu.marius.graph.mappers.user.UserMapper;
import edu.marius.graph.services.MatcherService;
import edu.marius.graph.services.CvService;
import edu.marius.graph.services.JobDescriptionService;
import edu.marius.graph.services.JobSummaryService;
import edu.marius.graph.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author MariusCraciunescu
 */
@Configuration
@Import(MyNeo4jConfiguration.class)
@RestController("/")
public class Main extends WebMvcConfigurerAdapter {

    private static final String APPLICATION_JSON = "application/json";

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    JobSummaryService jobSummaryService;

    @Autowired
    CvService cvService;

    @Autowired
    UserService userService;

    @Autowired
    CvMapper cvMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    JobDescriptionMapper jobDescriptionMapper;

    @Autowired
    WebCrawlerService webCrawlerService;

    @Autowired
    MatcherService matcherService;

    @Autowired
    JobDescriptionService jobDescriptionService;

    @RequestMapping("/put")
    public String put(@RequestParam(value = "Name") String name) {
        Cv p = new Cv();
        p.setFirstName(name);
        cvService.create(p);
        return "cv added";
    }

    @RequestMapping("/get/matchingJobs")
    public List<JobSummary> getMatchingJobs(@RequestParam(value = "id") Long id) {
        return matcherService.getMatchingJobsSummaries(id);
    }

    @RequestMapping(value = "/get/cv", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public CvType getCv(@RequestParam(value = "id") Long id) {
        return cvMapper.map(cvService.findById(id));
    }

    @RequestMapping(value = "/create/cv", method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public String create(@RequestBody CvType cvType) {
        Cv p = cvMapper.map(cvType);
        cvService.create(p);
        return "success";
    }

    @RequestMapping(value = "/create/user", method = RequestMethod.POST,
            consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public String create(@RequestBody UserType userType) {
        if (userService.findByName(userType.getName()) != null) {
            return "failure";
        }
        userService.create(userMapper.map(userType));
        return "success";
    }

    @RequestMapping(value = "/assignCv", method = RequestMethod.PUT,
            consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public String assignCv(@RequestBody UserType user) {
        userService.assignCvIdToUser(user.getId(), user.getCvId());
        return "success";
    }

    @RequestMapping(value = "/get/user", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public UserType getUser(@RequestParam(value = "name") String name) {
        return userMapper.map(userService.findByName(name));
    }

    @RequestMapping(value = "/get/jobs", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public List<JobSummary> getAllJobs() {
        return jobSummaryService.getJobSummaries();
    }

    @RequestMapping(value = "/get/job", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public JobPosting getJob(@RequestParam(value = "id") Long id) {
        return jobDescriptionMapper.map(jobDescriptionService.findById(id));
    }

    @RequestMapping(value = "/crawl", method = RequestMethod.PUT, consumes = APPLICATION_JSON)
    public String crawl(@RequestParam(value = "domain") String domain,
            @RequestParam(value = "pages") String pages,
            @RequestParam(value = "limit") String limit,
            @RequestParam(value = "startPage") String startPage) {
        boolean success = webCrawlerService.crawl(domain, startPage, pages, limit);
        if (success) {
            return "success";
        }
        return "fail";
    }
}
