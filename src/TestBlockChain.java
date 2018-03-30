import java.util.ArrayList;
import com.google.gson.GsonBuilder;


public class TestBlockChain {

	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	//Set mining difficulty ( difficulty increases for largest numbers)
	public static int difficulty = 7;
	
	public static void main(String[] args) {
		
		//Attempt to add first genesis block
		blockchain.add(new Block("Genesis Block", "0"));
		System.out.println("Trying to mine Genesis Block... ");
		blockchain.get(0).mineBlock(difficulty);
		
		//Attempt to add second block
		blockchain.add(new Block("This is block 2",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to mine Block 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		//Attempt to add third block
		blockchain.add(new Block("This is block 3",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to mine Block 3... ");
		blockchain.get(2).mineBlock(difficulty);	
		
		//Check if the blockchain iis valid after adding blocks
		System.out.println("\nBlockchain is Valid: " + isValidChain());
		
		//Print contents of all blocks
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	
	}
	
	//Determine if the the blockchain is valid by comparing the hashes of each block
	public static Boolean isValidChain() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for(int i = 1; i < blockchain.size(); i ++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);
			
			if(!currentBlock.hash.equals(currentBlock.calculateHash() )) {
				System.out.println("Current hashes are not equal");
				return false;
			}
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous hashes are not equal");
				return false;
			}
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}

}//end class
