package org.htp.ex.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MainService {

    public UriComponents uriComponents (RedirectAttributes redirectAttributes,String referer) {
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams().entrySet().forEach(pair ->
                redirectAttributes.addAttribute(pair.getKey(),pair.getValue()));

        return components;
    }
}
