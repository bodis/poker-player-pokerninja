package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.leanpoker.player.model.GameState;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class PlayerServlet extends HttpServlet {


    static IPlayer player = new PokerPlayer16();
    static IPlayer fallbackPlayer = new PokerPlayer15();

    
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("Java player is running");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            try {
                doPost(req, resp, player);
            } catch (Throwable e) {
                System.err.println("HIBA!!! tortent: " + e.getMessage());
                e.printStackTrace();
                doPost(req, resp, fallbackPlayer);
            }
        } catch (Throwable t) {
            System.err.println(" ----> FALLBACK - HIBA!!! tortent: " + t.getMessage());
            t.printStackTrace();
            doPost(req, resp, new IPlayer() {
                @Override
                public int betRequest(GameState game) {
                    return 0;
                }

                @Override
                public void showdown(JsonElement game) {

                }

                @Override
                public String getVersion() {
                    return "0000";
                }
            });

        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp, IPlayer player) throws ServletException, IOException {
    	long startTime = System.nanoTime();
    	try {
    		final String action = req.getParameter("action");
    		
    		System.err.println("action : " + action);
    		System.err.println("player version : " + player.getVersion());
    		
    		if (action.equals("bet_request")) {
    			String gameState = req.getParameter("game_state");
    			System.err.println("\n" + gameState + "\n");
    			Gson gson = new GsonBuilder().create();
    			int result = player.betRequest(gson.fromJson(gameState, GameState.class));
    			System.err.println("\n valasz: " + result + "\n");
    			resp.getWriter().print(result);
    		}
    		if (action.equals("showdown")) {
    			String gameState = req.getParameter("game_state");
    			
    			player.showdown(new JsonParser().parse(gameState));
    		}
    		if (action.equals("version")) {
    			resp.getWriter().print(player.getVersion());
    		}
    	} catch (IOException | RuntimeException e) {
    		System.err.println("HIBA; player version : " + player.getVersion());
    		throw e;
    	} finally {
    		long endTime = System.nanoTime();
    		System.err.println("Execution time in nanos : " + (endTime - startTime));
    	}
    }
}
