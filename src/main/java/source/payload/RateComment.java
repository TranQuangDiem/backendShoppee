package source.payload;

import lombok.Data;

@Data
public class RateComment {
   int id;
   String value;
   String status;
   int sumCmt;
}
