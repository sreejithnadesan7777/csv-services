package starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import starter.Service.CsvExtractService;
import starter.Service.FileListService;
import starter.Service.LoadFileService;
import starter.dto.CsvValidData;
import starter.dvo.Response;

import java.util.List;

@Controller
public class CsvServiceController {
  @Autowired
  @Qualifier("csvExtractServiceImpl")
  private CsvExtractService csvExtractService;

  @Autowired
  @Qualifier("loadFileServiceImpl")
  private LoadFileService loadFileService;

  @Autowired
  @Qualifier("fileListServiceImpl")
  private FileListService fileListService;


  @RequestMapping("/")
  public ModelAndView showUpload() {
    ModelAndView modelAndView =  new ModelAndView("index");
    return modelAndView;
  }

  @RequestMapping(value = "upload", method = RequestMethod.POST, produces = "application/json")
  public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
    Response response = csvExtractService.execute(file);
    ModelAndView modelAndView =  new ModelAndView("index", "message", response);
    return modelAndView;
  }

  @RequestMapping("/list")
  public ModelAndView filelisting() {
    ModelAndView modelAndView =  new ModelAndView("list");
    modelAndView.addObject("files", loadFileService.execute());
    return modelAndView;
  }

  @RequestMapping(value = "listing", method = RequestMethod.POST, produces = "application/json")
  public ModelAndView handleFileListing(@RequestParam("filename") String filename, RedirectAttributes redirectAttributes) throws Exception {
    List<CsvValidData> csvValidDataList = fileListService.execute(filename);
    ModelAndView modelAndView =  new ModelAndView("list");
    modelAndView.addObject("files", loadFileService.execute());
    modelAndView.addObject("csvValidDataList", csvValidDataList);
    return modelAndView;
  }
}
