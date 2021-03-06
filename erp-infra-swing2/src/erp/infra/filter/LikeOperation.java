package erp.infra.filter;

/**
 * LikeOperation class.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (04/02/2013 21:50)
 */
public class LikeOperation extends Operation {
    
    
    public LikeOperation() {
        setName("like");
        setQuery("UPPER({field}) like UPPER({value})");
    }
    
}
