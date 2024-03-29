package com.gmw.view.tos;

import com.gmw.field.tos.NewFieldTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewViewTO {
    private Long gameId;
    private List<NewFieldTO> fields;
}
