/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.graph.mappers.cv;

import edu.marius.graph.domain.cv.Language;
import edu.marius.graph.entity.LanguagesType;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MariusCraciunescu
 */
@Service
@Transactional
public class LanguageMapper {

    public List<Language> map(LanguagesType languagesType) {
        return languagesType.getLanguage()
                .stream()
                .map(languageType -> new Language(languageType))
                .collect(Collectors.toList());
    }

    public LanguagesType map(List<Language> langs) {
        LanguagesType languagesType = new LanguagesType();
        langs.forEach(language -> languagesType.getLanguage().add(language.getName()));
        return languagesType;
    }
}
