package linn.core;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Linn {
	// meta information
	private String name;
	private String author;
	private Date date;
	// rules
	final Map<String, List<Integer>> ruleIdsOfRuleName = Maps.newHashMap();
	final Map<Integer, Float> weightOfRuleId = Maps.newHashMap();
	
	public Linn() {
		this.setDate(new Date());
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Date getDate() {
		return date;
	}
	
	protected void setDate(Date date) {
		this.date = date;
	}
	
	public void addRule(Integer ruleId, String ruleName) {
		this.ruleIdsOfRuleName.putIfAbsent(ruleName, Lists.<Integer>newArrayList());
		List<Integer> ruleIds = this.ruleIdsOfRuleName.get(ruleName);
		ruleIds.add(ruleId);
		// set default weight, if none is set yet
		this.weightOfRuleId.putIfAbsent(ruleId, 1.0f);
	}
	
	public void setRuleWeight(Integer ruleId, float weight) {
		this.weightOfRuleId.put(ruleId, weight);
	}

	// init
	// hooks/handlers
	// production
	// production listeners
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("linn '" + this.name + "' (");
		if(this.author != null) {
			sb.append(this.author + ", ");
		}
		sb.append(this.date + ") {\n");
		for(Entry<String, List<Integer>> ruleEntry : this.ruleIdsOfRuleName.entrySet()) {
			String ruleName = ruleEntry.getKey();
			for(Integer ruleId : ruleEntry.getValue()) {
				sb.append("\t" + ruleName + " --" + this.weightOfRuleId.get(ruleId) + "-> " + "\n");
			}
		}
		sb.append("}");
		return sb.toString();
	}
}
