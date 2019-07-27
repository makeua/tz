package home.maks.handler;

import home.maks.db.BulkInserter;
import home.maks.db.loading.FormStatRecordLoader;
import home.maks.db.loading.UserStatRecordLoader;
import home.maks.db.model.tables.records.FormStatRecord;
import home.maks.db.model.tables.records.UserStatRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Statistics {
    @Autowired
    private FormStatRecordLoader formStatLoader;
    @Autowired
    private UserStatRecordLoader userStatLoader;

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public void statistics(Model model,
                           @RequestParam("fileId") int fileId) {
        model.addAttribute("formStats", formStatLoader.load(fileId));
        model.addAttribute("userStats", userStatLoader.load(fileId));
    }
}
