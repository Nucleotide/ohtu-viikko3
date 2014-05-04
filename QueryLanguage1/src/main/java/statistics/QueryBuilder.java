/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package statistics;

import java.util.ArrayList;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Not;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;


/**
 *
 * @author joonaslaakkonen
 */
public class QueryBuilder {
    ArrayList<Matcher> builder;
    
    public QueryBuilder() {
        builder = new ArrayList<Matcher>();
    }
    
    public Matcher build() {
        Matcher[] ehdot = new Matcher[builder.size()];
        int i = 0;
        for (Matcher a : this.builder) {
            ehdot[i] = a;
            i++;
        }
        return new And(ehdot);
    }
    
    QueryBuilder playsIn(String team) {
        this.builder.add(new PlaysIn(team));
        return this;
    }
    
    QueryBuilder hasAtLeast(int amount, String field) {
        this.builder.add(new HasAtLeast(amount, field));
        return this;
    }
    
    QueryBuilder hasFewerThan(int amount, String field) {
        this.builder.add(new HasFewerThan(amount, field));
        return this;
    }
    
    QueryBuilder oneOf(Matcher... matchers) {
        this.builder.add(new Or(matchers));
        return this;
    }
    
    QueryBuilder none(Matcher... matchers) {
        this.builder.add(new Not(matchers));
        return this;
    }
    
}
