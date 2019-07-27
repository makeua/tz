package home.maks.handler;

import home.maks.db.loading.FileRecordLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Handler {
    @Autowired
    private FileRecordLoader fileLoader;

    @RequestMapping("/")
    public String redirect() {
        return "index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("files", fileLoader.getLastFiles(10));
        return "index";
    }

    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }
}
