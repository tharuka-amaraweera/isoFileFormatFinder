package castlab.assignment.isoFileFormat.controller;

import castlab.assignment.isoFileFormat.dto.Mp4Box;
import castlab.assignment.isoFileFormat.service.Mp4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mp4")
public class Mp4Controller {

    @Autowired
    Mp4Service mp4Service;

    @GetMapping("/analyze")
    public Mp4Box analyzeMp4File(@RequestParam String url){
        return mp4Service.analyzeMp4(url);
    }
}
