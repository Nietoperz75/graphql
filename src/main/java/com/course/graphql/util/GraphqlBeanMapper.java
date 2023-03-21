package com.course.graphql.util;

import com.course.graphql.datasource.problemz.entity.Problemz;
import com.course.graphql.datasource.problemz.entity.Solutionz;
import com.course.graphql.datasource.problemz.entity.Userz;
import com.course.graphql.datasource.problemz.entity.UserzToken;
import com.course.graphql.generated.types.*;
import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.ZoneOffset;
import java.util.List;

public class GraphqlBeanMapper {

    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(7);

    public static User mapToGraphql(Userz original){
        var result = new User();
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);

        result.setAvatar(original.getAvatar());
        result.setCreateDateTime(createDateTime);
        result.setDisplayName(original.getDisplayName());
        result.setEmail(original.getEmail());
        result.setId(original.getId().toString());
        result.setUserName(original.getUsername());
        return result;
    }
    public static Problem mapToGraphql(Problemz original){
        var result = new Problem();
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        var author = mapToGraphql(original.getCreatedBy());
        var convertedSolutions = original.getSolutions().stream().map(GraphqlBeanMapper::mapToGraphql).toList();
        var tagList = List.of(original.getTags().split(","));
        result.setAuthor(author);
        result.setContent(original.getContent());
        result.setCreateDateTime(createDateTime);
        result.setId(original.getId().toString());
        result.setSolutions(convertedSolutions);
        result.setSolutionCount(convertedSolutions.size());
        result.setTags(tagList);
        result.setTitle(original.getTitle());
        result.setPrettyCreateDateTime(PRETTY_TIME.format(createDateTime));
        return result;
    }

    public static Solution mapToGraphql(Solutionz original){
        var result = new Solution();
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        var author = mapToGraphql(original.getCreatedBy());
        var category = StringUtils.equalsIgnoreCase(original.getCategory(), SolutionCategory.EXPLANATION.toString()) ?SolutionCategory.EXPLANATION:SolutionCategory.REFERENCE;
        result.setAuthor(author);
        result.setCategory(category);
        result.setContent(original.getContent());
        result.setCreateDateTime(createDateTime);
        result.setId(original.getId().toString());
        result.setVoteAsBadCount(original.getVoteBadCount());
        result.setVoteAsGoodCount(original.getVoteGoodCount());
        result.setPrettyCreateDateTime(PRETTY_TIME.format(createDateTime));
        return result;
    }

    public static UserAuthToken mapToGraphql(UserzToken original){
        var result = new UserAuthToken();
        var expiryDateTime = original.getExpiryTimestamp().atOffset(ZONE_OFFSET);
        result.setAuthToken(original.getAuthToken());
        result.setExpiryTime(expiryDateTime);
        return result;
    }
}
