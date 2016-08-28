package com.javarush.CRUD_TestTask;

import java.util.Date;

import javax.servlet.annotation.WebServlet;



import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.vaadin.resetbuttonfortextfield.ResetButtonClickListener;
import org.vaadin.resetbuttonfortextfield.ResetButtonForTextField;

import com.javarush.CRUD_TestTask.DAO.UserDAO;
import com.javarush.CRUD_TestTask.model.User;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("com.javarush.CRUD_TestTask.MyAppWidgetset")
public class MyUI extends UI
{
	  private static final long serialVersionUID = 1L;

	  private ClickListener clickListenerRemoveButton;
	  private ClickListener clickListenerEditButton;
	  private ClickListener clickListenerAddButton;
	  private ValueChangeListener valueChangeListenrTable;
	  private Table users;
	  private Button buttonRemove;
	  private Button buttonEditUser;
	  private Button buttonAdd;
	  private VerticalLayout verticalLayoutMain;
	  private HorizontalLayout horizontalLayoutButtons;
	  
	  private HorizontalLayout searchLayout;
	  private Button searchButton;
	  private TextField searchField;
	  private ClickListener clickListenerSearchButton;
	  private ResetButtonForTextField resetButtonForSearch;
	  private ResetButtonClickListener resetButtonListener;
	  
	  private HorizontalLayout pagination;
	  private Button pageNext;
	  private Button pageBack;
	  private Button pageLast;
	  private Button pageFirst;
	  private ClickListener clickListenerPageNext;
	  private ClickListener clickListenerPageBack;
	  private ClickListener clickListenerPageLast;
	  private ClickListener clickListenerPageFirst;
	  private TextField currentPage;
	  private Property.ValueChangeListener valueChangeListenrCurrentPage;
	  private Label numberOfPagesLabel;
	  private ComboBox selectRowsPerPage;
	  private Property.ValueChangeListener valueChangeListenrSelectRowsPerPage;
	  private int rowsPerPage;
	  private int beginRow;
	  private IntegerRangeValidator validatorPagination;
	  private Integer numberOfPages;
	  
	  
	  	  
	  
	  
	  private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");
  	
	  private UserDAO userDAO =  context.getBean(UserDAO.class);
	  
	  private void setUpPagination()
	  {
		  selectRowsPerPage = new ComboBox();
		  selectRowsPerPage.addItem("6");
		  selectRowsPerPage.addItem("12");
		  selectRowsPerPage.addItem("24");
		  selectRowsPerPage.addItem("48");
		  selectRowsPerPage.setImmediate(true);
		  selectRowsPerPage.setNullSelectionAllowed(false);
		  selectRowsPerPage.setWidth("80px");

		  selectRowsPerPage.select("12");
		  
		  
		  //lala
		  
		  
		  currentPage = new TextField();
		  
		  currentPage.setConverter(Integer.class);
		  currentPage.setWidth("40px");
		  currentPage.setImmediate(true);
		  setNumberOfPages();
		  validatorPagination = new IntegerRangeValidator("Wrong page number", 1, numberOfPages);
		  currentPage.addValidator(validatorPagination);
		  
		  
		  
		  setUpPaginationListeners();
		  pageNext = new Button(">",clickListenerPageNext);
		  pageBack = new Button("<",clickListenerPageBack);
		  
		  selectRowsPerPage.addValueChangeListener(valueChangeListenrSelectRowsPerPage);
		  
		  currentPage.addValueChangeListener(valueChangeListenrCurrentPage);
		  pageLast = new Button(">>",clickListenerPageLast);
		  pageFirst = new Button("<<",clickListenerPageFirst);
		  
		  numberOfPagesLabel = new Label("of " + numberOfPages.toString());
		  numberOfPagesLabel.setStyleName("newLabel");

		  HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
		  tempHorizontalLayout.addComponents(pageFirst,pageBack,currentPage,numberOfPagesLabel,pageNext,pageLast);
		  tempHorizontalLayout.setSpacing(true);
		  
		  pagination.addComponents(selectRowsPerPage,tempHorizontalLayout);
		  
		  pagination.setComponentAlignment(selectRowsPerPage, Alignment.MIDDLE_LEFT);
		  pagination.setComponentAlignment(tempHorizontalLayout, Alignment.MIDDLE_RIGHT);
		  //pagination.setComponentAlignment(pageFirst, Alignment.MIDDLE_RIGHT);
		  //pagination.setSpacing(true);
		  //pagination.setMargin(true);
	  }
	  
	  private void setUpPaginationListeners()
	  {
		  valueChangeListenrSelectRowsPerPage = new Property.ValueChangeListener() {
			  	private static final long serialVersionUID = 1L;

	            public void valueChange(
	                    com.vaadin.data.Property.ValueChangeEvent event) {
	            	
	                rowsPerPage = (Integer.valueOf(String.valueOf(event
	                        .getProperty().getValue())));
	                
	                users.setPageLength(rowsPerPage);
    				generateCharacter(beginRow,rowsPerPage);
	            }
	        };
		  
		  clickListenerPageNext = new Button.ClickListener() {
			
			  	private static final long serialVersionUID = 1L;
			  
				@Override
				public void buttonClick(ClickEvent event) {
					beginRow = beginRow + rowsPerPage;
					generateCharacter(beginRow,rowsPerPage);
					
				}
		  };
		  
		  clickListenerPageBack = new Button.ClickListener() {
				
			  	private static final long serialVersionUID = 1L;
			  
				@Override
				public void buttonClick(ClickEvent event) {
					beginRow = beginRow - rowsPerPage;
					generateCharacter(beginRow,rowsPerPage);
					
				}
		  };
		  
		  clickListenerPageLast = new Button.ClickListener() {
				
			  	private static final long serialVersionUID = 1L;
			  
				@Override
				public void buttonClick(ClickEvent event) {
					beginRow = ((((int)(long)userDAO.getNumberOfRows())-1)/rowsPerPage)*rowsPerPage;
					System.out.println(beginRow);
					generateCharacter(beginRow,rowsPerPage);
					
				}
		  };
		  
		  clickListenerPageFirst = new Button.ClickListener() {
				
			  	private static final long serialVersionUID = 1L;
			  
				@Override
				public void buttonClick(ClickEvent event) {
					beginRow = 0;
					generateCharacter(beginRow,rowsPerPage);
					
				}
		  };
		  
		  valueChangeListenrCurrentPage = new Property.ValueChangeListener() {
				
			  	private static final long serialVersionUID = 1L;

				@Override
				 public void valueChange(
		                    com.vaadin.data.Property.ValueChangeEvent event) {
					if (currentPage.isValid()
	                        && currentPage.getValue() != null) {
	                    int page = Integer.valueOf(String
	                            .valueOf(currentPage.getValue()));
	                    
	                    int temp = (page-1)*rowsPerPage;

	                    beginRow = temp;
	                    users.setPageLength(rowsPerPage);
	    				generateCharacter(beginRow,rowsPerPage);
	                }
		            }
			  
				
		  };
	  }
	  
	  private void setNumberOfPages()
	  {
		  int temp = (int)(long)userDAO.getNumberOfRows();
				  
		  numberOfPages = temp/rowsPerPage;
		  if(temp%rowsPerPage > 0)
		  {
			  numberOfPages++;
		  }
	  }
	  @Override
	  protected void init(VaadinRequest request)
	  {
	    setUpEventHandling();
	    setUpGui();
	  }
	  
	  private void setUpGui()
	  {
	    buttonRemove = new Button("Remove", clickListenerRemoveButton);
	    buttonEditUser = new Button("Edit User", clickListenerEditButton);
	    buttonAdd = new Button("Add", clickListenerAddButton);
	    
	    searchLayout = new HorizontalLayout();
	    searchButton = new Button("Search",clickListenerSearchButton);
	    searchField = new TextField();
	    resetButtonForSearch = ResetButtonForTextField.extend(searchField);
	    resetButtonForSearch.addResetButtonClickedListener(resetButtonListener);
	    searchField.setInputPrompt("Search by Name");
	    searchLayout.addComponents(searchField,searchButton);
	    
	    
	    horizontalLayoutButtons = new HorizontalLayout();
	    horizontalLayoutButtons.addComponent(buttonAdd);
	    horizontalLayoutButtons.addComponent(buttonEditUser);
	    horizontalLayoutButtons.addComponent(buttonRemove);
	    horizontalLayoutButtons.addComponent(searchLayout);
	    
	    horizontalLayoutButtons.setSpacing(true);
	    horizontalLayoutButtons.setMargin(true);
	    
	    horizontalLayoutButtons.setMargin(new MarginInfo(true,false,false,false));
	    
	    horizontalLayoutButtons.setStyleName("mymargins");
	    
	    
	    users = new Table("CRUD");
	    users.setWidth(70, Unit.PERCENTAGE);
	    users.addContainerProperty("Id", Long.class, null);
	    users.addContainerProperty("Name", String.class, null);
	    users.addValueChangeListener(valueChangeListenrTable);
	    
	    //Default values of pagination
	    beginRow = 0;
	    rowsPerPage = 12;
	    
	    pagination = new HorizontalLayout();
	    setUpPagination();
	    
	    generateCharacter(beginRow,rowsPerPage);
	    
	    users.setPageLength(rowsPerPage);
	    users.setSelectable(true);
	    users.setImmediate(true);
	    //users.setFooterVisible(true);
	    //users.setColumnFooter("Race", null);
	    //users.setColumnFooter("Class", "Sum of party levels");
	    
	    
	    
	    verticalLayoutMain = new VerticalLayout();
	    setContent(verticalLayoutMain);
	    verticalLayoutMain.addComponent(horizontalLayoutButtons);
	    
	    verticalLayoutMain.addComponent(users);
	    verticalLayoutMain.addComponent(pagination);
	    verticalLayoutMain.setComponentAlignment(horizontalLayoutButtons, Alignment.MIDDLE_CENTER);
	    verticalLayoutMain.setComponentAlignment(users,Alignment.MIDDLE_CENTER);
	    verticalLayoutMain.setComponentAlignment(pagination,Alignment.MIDDLE_CENTER);
	    pagination.setWidth(70, Unit.PERCENTAGE);
	    

	    
	  }
	  
	  private void setUpEventHandling()
	  {
	    clickListenerRemoveButton = new Button.ClickListener()
	    {
	      
	      private static final long serialVersionUID = 1L;

	      @Override
	      public void buttonClick(ClickEvent event)
	      {
	        Object selected = users.getValue();
	        if(selected == null)
	        {
	          Notification.show("You must select an item");
	        }
	        else
	        {
	          userDAO.deleteById((Long)users.getContainerProperty(selected,"Id").getValue()); //table.getContainerProperty(rowId,"Email").getValue();
	          users.removeItem(selected);
	          users.setPageLength(rowsPerPage);
          	generateCharacter(beginRow,rowsPerPage);
          	if(beginRow >= (int)(long)userDAO.getNumberOfRows()) pageBack.click();
	          Notification.show("Done!");
	        }
	      }
	    };
	    
	    clickListenerEditButton = new Button.ClickListener()
	    {
	      
	      private static final long serialVersionUID = 1L;

	      @Override
	      public void buttonClick(ClickEvent event)
	      {
	        Object selected = users.getValue();
	        if(selected == null)
	        {
	          Notification.show("You must select an item");
	        }
	        else
	        {
//	          stockBo.deleteById((Long)users.getContainerProperty(selected,"Id").getValue()); //table.getContainerProperty(rowId,"Email").getValue();
//	          users.removeItem(selected);
	        	
	        	User data = userDAO.getById((Long)users.getContainerProperty(selected,"Id").getValue());
	        	editUser(data);
	          //Notification.show("Done!");
	        }
	      }
	    };
	    
	    clickListenerAddButton = new Button.ClickListener()
	    {
	      private static final long serialVersionUID = 1L;

	      @Override
	      public void buttonClick(ClickEvent event)
	      {
    	  
	    	  editUser(null);
	            
	        
	      }
	      
	      
	    };
	    
	    valueChangeListenrTable = new ValueChangeListener()
	    {
	      private static final long serialVersionUID = 1L;

	      @Override
	      public void valueChange(ValueChangeEvent event)
	      {
	        if(users.getValue()!=null)
	        Notification.show("Selected item : " + users.getContainerProperty(users.getValue(),"Id").getValue());
	      }
	    };
	   
	    
	    clickListenerSearchButton = new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {

				if(searchField.getValue()!=null&&searchField.getValue()!="")searchUsers(searchField.getValue());
				else Notification.show("You must write search value firstly");
				System.out.println("Search value: " + searchField.getValue());
				pagination.setEnabled(false);
				
			}
		};
		
		
		resetButtonListener = new ResetButtonClickListener(){

		      private static final long serialVersionUID = 1L;
		      
			@Override
			public void resetButtonClicked() {
				// TODO Auto-generated method stub
				users.setPageLength(rowsPerPage);
				generateCharacter(beginRow,rowsPerPage);
				System.out.println("Clicked reset button");
				pagination.setEnabled(true);
			}
		};
	    
	  }
	  
	  private void generateCharacter(int firstResult,int maxResults)
	  {
		System.out.print("Loading:");
		users.removeAllItems();
		users.clear();
		
		for(User data : userDAO.getListLazy(firstResult,maxResults))
        {
			
			
        	//table.addItem(new Object[]{data.getId(),data.getName()}, data.getId()); //(int)(data.getId()+0)
			
			Object newItemId = users.addItem();
		    Item row1 = users.getItem(newItemId);
		    row1.getItemProperty("Id").setValue(data.getId());
		    row1.getItemProperty("Name").setValue(data.getName());

        	
        	System.out.print(".");
        	
        }
		System.out.print("Done!\n");
		
		if(beginRow < rowsPerPage)
		{
			pageBack.setEnabled(false);
			pageFirst.setEnabled(false);
		}
		else 
		{
			pageBack.setEnabled(true);
			pageFirst.setEnabled(true);
		}
		
		if(beginRow > (int)(long)userDAO.getNumberOfRows() - rowsPerPage-1)
		{
			pageNext.setEnabled(false);
			pageLast.setEnabled(false);
		}
		else 
		{
			pageNext.setEnabled(true);
			pageLast.setEnabled(true);
		}
		currentPage.setValue(beginRow/rowsPerPage+1+"");
		setNumberOfPages();
		validatorPagination.setMaxValue(numberOfPages);
		
		numberOfPagesLabel.setValue(" of " + numberOfPages.toString());
		
		
		
	  }
	  
	  private void searchUsers(String name)
	  {
		
		users.removeAllItems();
		users.clear();
		for(User data : userDAO.findByName(name))
        {
			Object newItemId = users.addItem();
		    Item row1 = users.getItem(newItemId);
		    row1.getItemProperty("Id").setValue(data.getId());
		    row1.getItemProperty("Name").setValue(data.getName());
        }
	  }
	  
	  private void editUser(User user)
	  {
		  String windowName;
		  
		  if(user == null)
		  {
			  windowName = "Adding User";
		  }
		  else windowName = "Editing User";
		  
		  
		  Window subWindow = new Window(windowName);
          VerticalLayout subContent = new VerticalLayout();
          subContent.setMargin(true);
          subWindow.setContent(subContent);
          subWindow.setModal(true);
                      

          // Put some components in it
          
          FormLayout form = new FormLayout();
          TextField tf1 = new TextField("Name");
          
          tf1.setIcon(FontAwesome.USER);
          tf1.setRequired(true);
          tf1.addValidator(new NullValidator("Must be given", false));
          form.addComponent(tf1);

          TextField tf2 = new TextField("Age");
          tf2.setIcon(FontAwesome.CHILD);
          tf2.setRequired(true);
          tf2.addValidator(new RegexpValidator("([0-1][0-4][0-9])|([0-9][0-9])|[1-9]", "You should enter correct value!"));
          form.addComponent(tf2);
          
          
          CheckBox isAdmin = new CheckBox("Is User admin?", false);
          form.addComponent(isAdmin);

          PopupDateField dateOfCreation = new PopupDateField();
          dateOfCreation.setValue(new Date());
          dateOfCreation.setResolution(Resolution.MINUTE);
          //dateOfCreation.setDateFormat("dd.MM.yyyy HH:mm");
          form.addComponent(dateOfCreation);
          
          if(user != null)
          {
        	  tf1.setValue(user.getName());
        	  tf2.setValue(user.getAge().toString());
        	  isAdmin.setValue(user.getIsAdmin());
        	  dateOfCreation.setValue(user.getCreateDate());
          }
          
          Button saveUser = new Button("Save");
          form.addComponent(saveUser);
          
          saveUser.addClickListener(s -> {
          	if(tf2.isValid())
          	{
	            	User stock;
	            	if(user != null) stock = user;
	            	else stock = new User();
	            	stock.setName(tf1.getValue());
	            	stock.setAge(Integer.parseInt(tf2.getValue()));
	            	stock.setIsAdmin(isAdmin.getValue());
	            	stock.setCreateDate(dateOfCreation.getValue());
	            	if(user == null) userDAO.save(stock);

	            	
	            	else userDAO.update(stock);
	            	users.setPageLength(rowsPerPage);
	            	generateCharacter(beginRow,rowsPerPage);
	            	if(user == null) pageLast.click();
	            	
	            	subWindow.close();
	            	if(user == null)
	            	Notification.show("Added a row");
	            	else Notification.show("Changes saved");
          	}
          });
          
          subContent.addComponent(form);
          
          
          
      	


          // Center it in the browser window
          subWindow.center();
          
          subWindow.setSizeUndefined();
          subContent.setSizeUndefined();
          form.setSizeUndefined();

          // Open it in the UI
          addWindow(subWindow);
          buttonAdd.setEnabled(false);
          
          
          //enable button addUser when close the window
          subWindow.addCloseListener(d -> {
          	buttonAdd.setEnabled(true);
          });
		  
	  }
	 	  

//	  @Override
//	  public Component generateCell(Table source, Object itemId, Object columnId)
//	  {
//	    Property prop = source.getItem(itemId).getItemProperty(columnId);
//	    if(prop.getType().equals(Integer.class) && prop != null)
//	    {
//	      int val = (int)prop.getValue();
//	      Label customLabel = new Label();
//	      if(val < 10)
//	      {
//	        customLabel.setValue("bad");
//	      }
//	      if(val >= 10 && val < 15)
//	      {
//	        customLabel.setValue("medium");
//	      }
//	      if(val >= 15 && val < 18)
//	      {
//	        customLabel.setValue("good");
//	      }
//	      if(val >= 18)
//	      {
//	        customLabel.setValue("best");
//	      }
//	      return customLabel;
//	    }
//	    return null;
//	  }

	


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false, widgetset="com.javarush.CRUD_TestTask.MyAppWidgetset")
    public static class MyUIServlet extends VaadinServlet {
    }
}
