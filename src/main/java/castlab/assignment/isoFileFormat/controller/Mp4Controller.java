package castlab.assignment.isoFileFormat.controller;

import castlab.assignment.isoFileFormat.dto.Mp4Box;
import castlab.assignment.isoFileFormat.service.Mp4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Mp 4 controller.
 */
@RestController
@RequestMapping("mp4")
public class Mp4Controller {

    /**
     * The Mp4 service.
     */
    @Autowired
    Mp4Service mp4Service;

    /**
     * Analyze mp 4 file mp 4 box.
     *
     * @param url the url
     * @return the mp 4 box
     */
    @GetMapping("/analyze")
    public Mp4Box analyzeMp4File(@RequestParam String url){
        return mp4Service.analyzeMp4(url);
    }
}
