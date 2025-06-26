//package org.example.backend.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class InventoryDTO {
//    public Long id;
//    public Integer quantity;
//    public String location;
//}
package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryDTO {
    public Long id;
    public Integer quantity;
    public String location;
    public Long productId;
}