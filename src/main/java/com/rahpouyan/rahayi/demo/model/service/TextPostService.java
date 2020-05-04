package com.rahpouyan.rahayi.demo.model.service;

import com.rahpouyan.rahayi.demo.commom.CalendarTool;
import com.rahpouyan.rahayi.demo.model.entity.TextPost;
import com.rahpouyan.rahayi.demo.model.repository.TextPostDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextPostService {

    @Autowired
    TextPostDA textPostDA;


    public void save(TextPost textPost) {
        CalendarTool calendarTool = new CalendarTool();

        textPost.setReleaseDate(calendarTool.getAmirFormat());
        textPost.setReleaseTime(calendarTool.getTime());
        textPost = detectText(textPost);
        textPostDA.save(textPost);
    }

    public void update(TextPost textPost) {
        textPostDA.update(textPost);
    }

    public void delete(TextPost textPost) {
        textPostDA.delete(textPost);
    }

    public TextPost findOne(Long id) {
        return textPostDA.findOne(TextPost.class, id);
    }

    public List<TextPost> findAll(int index, int lenght, String type) {
        return textPostDA.findAll(index, lenght, type);
    }

    public List<TextPost> findAll7(String type) {
        return textPostDA.findAll7(type);
    }

    public Long getCount(String type) {
        return textPostDA.count(type);
    }

    public Long generateSequenceID() {
        return textPostDA.getSequence();
    }

    public void addViewNumber(long id) {
        textPostDA.addViewNumber(id);
    }

    private TextPost detectText(TextPost textPost) {
        String text = textPost.getText();
        String lines[] = text.split("\n");
        int j = 0;
        int i = 0;
        while ((j < 9) && (i < lines.length)) {
            String line = lines[i];
            if (!line.trim().equals("")) {
                if ((line.contains("ره آورد") || line.contains("ره اورد")) && line.contains(":")) {
                    textPost.setRahavard(line.split(":")[1].trim());
                }
                if ((line.contains("ره آموز") || line.contains("ره اموز")) && line.contains(":")) {
                    textPost.setRahamooz(line.split(":")[1].trim());
                }
                if ((line.contains("ره نگار") || line.contains("رهنگار")) && line.contains(":")) {
                    textPost.setRahnegar(line.split(":")[1].trim());
                }
                if ((line.contains("نمایندگی")) && line.contains(":")) {
                    textPost.setNamayandegi(line.split(":")[1].trim());
                }
                j++;
            }
            i++;
        }
        if ((textPost.getNamayandegi() != null) || (textPost.getRahnegar() != null) || (textPost.getRahavard() != null) || (textPost.getRahamooz() != null)) {
            try {
                textPost.setText(textPost.getText().substring(textPost.getText().indexOf("\r\n\r\n") + 4));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return textPost;
    }


}
