/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.services;

import edu.marius.graph.domain.job.JobDescription;
import edu.marius.graph.entity.JobPosting;
import edu.marius.graph.mappers.job.JobDescriptionMapper;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MariusCraciunescu
 */
@Service
@Transactional
public class WebCrawlerService {

    private static final Logger LOG = Logger.getLogger(WebCrawlerService.class.getName());

    @Autowired
    JobDescriptionMapper mapper;

    @Autowired
    JobDescriptionService jobDescriptionService;

    private static final String hipoURL = "https://www.hipo.ro";
    private static final String baseURL = "https://www.hipo.ro/locuri-de-munca/cautajob/";
    private static final String suffix = "/Toate-Orasele/english/";

    private static final DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public boolean crawl(String domain, String startPage, String pages, String limit) {
        int count;
        if (limit == null || limit.isEmpty()) {
            count = Integer.MAX_VALUE;
        } else {
            count = Integer.parseInt(pages);
        }

        try {
            doCrawl(domain, Integer.parseInt(startPage), Integer.parseInt(pages), count);
        } catch (NumberFormatException | IOException | JAXBException | ParseException ex) {
            Logger.getLogger(WebCrawlerService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    private void doCrawl(String domain, int startPage, int pages, int limit) throws IOException, JAXBException,
            ParseException {
        Document webPage;
        final Unmarshaller unmarshaller = jsonUnmarshaller();
        int cnt = 0;

        String baseLink = constructBaseLink(domain);
        for (int i = startPage; i <= startPage + pages; i++) {

            webPage = Jsoup.connect(baseLink + i).get();

            final Elements jobs = webPage.getElementsByAttributeValue("itemtype", "http://schema.org/JobPosting");

            for (final Element job : jobs) {
                if (cnt++ < limit) {
                    continue;
                }
                final String jobURL = job.getElementsByAttributeValue("itemprop", "url").attr("href");

                final Document jobPage = Jsoup.connect(hipoURL + jobURL).get();
                LOG.log(Level.INFO, "Connected to {}", hipoURL + jobURL);

                final String jsonString = jobPage.getElementsByAttributeValue("type", "application/ld+json")
                        .get(0)
                        .data()
                        .trim();
                final StreamSource json = new StreamSource(new StringReader(jsonString));

                final JobPosting jobPosting = unmarshaller.unmarshal(json, JobPosting.class).getValue();
                LOG.log(Level.INFO, "Got job description \n {}", jobPosting);

                final JobDescription jd = mapper.map(jobPosting);
                jobDescriptionService.create(jd);

//                if (++cnt > limit) {
//                    LOG.log(Level.INFO, "Limit {} reached. Count = {}. Exiting", new Object[]{limit, cnt});
//                    return;
//                }
            }
        }
    }

    private static Unmarshaller jsonUnmarshaller() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JobPosting.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
        return unmarshaller;
    }

    private String constructBaseLink(String domain) {
        return baseURL + domain + suffix;
    }
}
