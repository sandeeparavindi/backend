//package org.example.backend.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.math.BigDecimal;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class ProductDTO {
//    public Long id;
//    public String name;
//    public String description;
//    public BigDecimal price;
//    public InventoryDTO inventory;
//}
package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    public Long id;
    public String name;
    public String description;
    public BigDecimal price;
    public List<InventoryDTO> inventories;
}