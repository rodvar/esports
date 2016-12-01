package com.rodvar.esports;

import com.rodvar.esports.data.model.Sport;
import com.rodvar.esports.data.model.SportList;
import com.rodvar.esports.data.model.Workspace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * Used as an example on how to test model
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainUnitTest {

    @Test
    public void testSportListHas0SizeOnCreation() {
        SportList sportList = new SportList(new Workspace());
        assertEquals(0, sportList.size());
    }

    @Test
    public void testSportListSizeWithSports() {
        List<Sport> sports = new ArrayList<>();
        sports.add(new Sport());
        sports.add(new Sport());
        SportList sportList = new SportList(new Workspace(sports));
        assertEquals(sports.size(), sportList.size());
    }

    @Test
    public void testInvalidSportList() {
        assertFalse(new SportList().isValid());
    }

    @Test
    public void testValidSportList() {
        assertTrue(new SportList(new Workspace()).isValid());
    }
}