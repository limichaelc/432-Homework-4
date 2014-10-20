
import java.io.FileNotFoundException;


public class TestBlockStoreMultiplexor {
 public static void main(String[] args) 
  throws FileNotFoundException, DataIntegrityException {
    BlockDevice wrappedStore = new BlockDevice("testDevice");
    wrappedStore.format();
    BlockStoreMultiplexor mux = new BlockStoreMultiplexor(wrappedStore);
    for(int i=0; i<7; ++i){
      BlockStore st = mux.newSubStore();
      boolean worked = TestBlockStore.test(st);
      if(! worked){
        System.out.println("Data failure");
      }
    }
    System.out.println("Done");
  }	
}