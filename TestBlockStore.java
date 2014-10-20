
public class TestBlockStore {
	public static byte[] makeBlock(BlockStore bs, int salt) {
		byte[] ret = new byte[bs.blockSize()];
		for(int i=0; i<bs.blockSize(); ++i){
			ret[i] = (byte)(salt+37*i);
		}
		return ret;
	}

	public static boolean test(BlockStore bs) throws DataIntegrityException {
		// Do some useful tests on a BlockStore
		// Return true iff tests are all passed
		bs.format();
		
		for(int bn=23; bn>=0; --bn){
			byte[] wrBlock = makeBlock(bs, bn);
			byte[] checkBlock = makeBlock(bs, bn);
			byte[] rdBlock = new byte[bs.blockSize()];
			bs.writeBlock(bn, wrBlock, 0, 0, bs.blockSize());
			bs.readBlock(bn, rdBlock, 0, 0, bs.blockSize());
			for(int i=0; i<bs.blockSize(); ++i){
				if(rdBlock[i]!=checkBlock[i]) {
					return false;
				}
			}
		}

		return true;
	}
}