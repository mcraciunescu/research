/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.services;

import edu.marius.graph.repositories.WordRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marius
 */
@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    public List<String> getSynonyms(String word) {
        Iterable words = wordRepository.getFirstDegreeSynonyms(word);
        List<String> synomyms = new ArrayList<>();
        Iterator it = words.iterator();
        while (it.hasNext()) {
            synomyms.addAll(((Map) it.next()).values());
        }

        return synomyms;
    }
}
