package test;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.mazeGenerators.Position;
import algorithms.search.BFSsearcher;
import algorithms.search.SearchSolution;

public class BFSsearcherTest {

	@Test
	//searchable is null
	public void test() {
		BFSsearcher<Position> test= new BFSsearcher<Position>();
		SearchSolution<Position> solution =test.Search(null);
		assertEquals(null,solution);
	}

}
