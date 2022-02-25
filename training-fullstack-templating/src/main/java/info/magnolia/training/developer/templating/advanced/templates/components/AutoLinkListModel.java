package info.magnolia.training.developer.templating.advanced.templates.components;
 
import info.magnolia.context.MgnlContext;
import info.magnolia.jcr.util.ContentMap;
import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.jcr.util.PropertyUtil;
import info.magnolia.rendering.model.RenderingModel;
import info.magnolia.rendering.model.RenderingModelImpl;
import info.magnolia.rendering.template.configured.ConfiguredTemplateDefinition;
import info.magnolia.repository.RepositoryConstants;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
 
import org.apache.commons.lang3.StringUtils;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * Model class for Navigation component.
 *
 * @param <RD>
 */
public class AutoLinkListModel<RD extends AutoLinkListDefinition> extends RenderingModelImpl<RD> {
     
    private static final Logger log = LoggerFactory.getLogger(AutoLinkListModel.class);
 
    public AutoLinkListModel(Node content, RD definition, RenderingModel<?> parent) {
        super(content, definition, parent);
    }
 
    private Node targetNode = null;
     
    @Override
    public String execute() {
        try {
            this.resolveTarget();
        } catch (RepositoryException e) {
            log.error("Was not able to resolve target node: "+e);
            return null;
        }
         
        return "success";
    }
 
    public String getTest() {
    
        return "test succeeded!";
    }
 
    // Using our PropertyUtil & NodeUtil for convenience coding
    private void resolveTarget() throws RepositoryException {
        System.out.println("Info: Here we will resolve the 'targetNode' uuid.");
        
        String idOfChosenTargetNode = PropertyUtil.getString(content, "targetNode");
        if (StringUtils.isNotEmpty(idOfChosenTargetNode)) {
            Node nodeByIdentifier = NodeUtil.getNodeByIdentifier(this.getWorkspaceName(), idOfChosenTargetNode);
            this.targetNode = nodeByIdentifier;
        }
    }
 
 
    public String getWorkspaceName() {
    	
    	String workspace = definition.getWorkspace();
        if (StringUtils.isEmpty(workspace)) {
            workspace = RepositoryConstants.WEBSITE;
        }
        return workspace;
    }
 
    public ContentMap getTargetNode() {
    	 if (targetNode != null) {
    	        return new ContentMap(targetNode);
    	    }
    	    return null;
    }
}