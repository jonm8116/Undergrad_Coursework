import bs4
from bs4 import BeautifulSoup
from urllib.request import urlopen
from termcolor import colored

class AcademicSubject():
    def __init__(self, name, url, major, minor, certificate):
        self.name = name
        self.url = url
        self.major = major
        self.minor = minor
        self.certificate= certificate

def mainMenu():
    wolfieDisplay = '''
   
    @@         @@  @@@    @@ @@@@@@ @@  @@@@@@@
     @@   @   @@  @   @   @@ @@         @@
      @@ @@@ @@  @     @  @@ @@@@   @@  @@@@
       @@@ @@@   @@   @@  @@ @@     @@  @@
        @   @     @@@@    @@ @@     @@  @@@@@@

        @@  @@  @@@@@@@ @@      @@@@@@
        @@  @@  @@      @@      @@   @
        @@@@@@  @@@@    @@      @@@@@@
        @@  @@  @@      @@      @@
        @@  @@  @@@@@@@ @@@@@@  @@
    
    '''
    wolfieMenu = '''
        (1) Academics
        (2) Clubs / Extracurriculars
        (3) Mental Health / CAPS
        (4) Opportunities 
        (5) Campus Life
        (6) Upcoming Events
        (7) What's a seawolf?
    '''
    print(wolfieDisplay)
    print(wolfieMenu)
    userInput = input("user-input>")
    return userInput

def what_is_a_seawolf():
    print("I'm a seawolf (and you are too!).")

def academicsSubMenu():
    subMenu = '''
    What would you like to know about?
        (1) Undergraduate
        (2) Graduate
    '''
    
    undergrad_list = []
    grad_list = []

    #   SETUP WEB SCRAPER
    academic_url = "https://www.stonybrook.edu/academics/majors-minors-and-programs/#UndergraduatePrograms" 
    client = urlopen(academic_url); html = client.read(); client.close()
    page_soup = BeautifulSoup(html, "html.parser")
    uls = page_soup.findAll("ul", {"class": "course-program-list"})
    
    print(subMenu) 
    userInput = int(input("user-input>"))

    #
    #   CREATE Undergrad list
    #
    if(userInput == 1):
        for li in uls[0].findAll('li'):
            try:
                textV = str(li)
                isMajor = "Major" in textV
                isMinor = "Minor" in textV
                isCertificate = "Certificate" in textV
                subj = AcademicSubject(li.a.text, li.a['href'], isMajor, isMinor, isCertificate)
                undergrad_list.append(subj)

            except Exception as e:
                print(e)
                continue
        print("Please type in a subject")
        userInput = input("user-input>")
        isFound = False
        for elem in undergrad_list:
            if(userInput.lower() in elem.name.lower()):
                isFound = True
                retStr = "Stony Brook has a "
                if(elem.major):
                    retStr += 'Major, '
                if(elem.minor):
                    retStr += 'Minor, '
                if(elem.certificate):
                    retStr += 'Certificate'

                retStr += ' for ' + colored(elem.name,'yellow') + '\nVisit this link for more info\n' + colored(elem.url,'red')
                print(retStr)

        
        if(not isFound):
            print("Sorry that subject couldn't be found")
        

if(__name__=='__main__'):
    print("starting main method")
    userInput = mainMenu()

    if(userInput == "1"):
        academicsSubMenu()

