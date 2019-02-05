package com.app.crud.feignClient;

import com.app.crud.dto.DocInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Component
@FeignClient(name="indexer")
public interface IndexerClient {
    @RequestMapping(value = "/updateindex", method = RequestMethod.POST)
    String keepIndex(@ModelAttribute(name="docinfo") DocInfo docInfo);
}
