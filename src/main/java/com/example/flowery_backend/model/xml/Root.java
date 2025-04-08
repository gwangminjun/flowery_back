package com.example.flowery_backend.model.xml;

import com.example.flowery_backend.model.Flower;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {

    private String resultCode;
    private String resultMsg;
    private int resultCnt;
    private int pageNo;
    private int numOfRows;
    private String repcategory;

    @XmlElement(name = "result")
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }
}
