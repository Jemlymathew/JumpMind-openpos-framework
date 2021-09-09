package org.jumpmind.pos.core.ui.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UIGraphMultiDataPoint implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<UIGraphSingleDataPoint> dataSeries;
}
