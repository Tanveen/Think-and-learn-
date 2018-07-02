import tkinter 
import socket 
import sqlite3 
import threading
import time
import tkinter.ttk 

class PortScannerDAL:
    def __init__(self):
        pass
    def __connect_(self):
        pass
    def read_host(self, host_ip, host_name = None):
        pass
    def create_host(self, host_ip, host_name):
        pass
    def create_scan(self, host_id):
        pass
    def update_scan_end_time(self, scan_id):
        pass
    def read_port_status(self, host_ip, host_name):
    
    def create_port_status(self, scan_id, port, is_open):
        if result == 0:
                tkmessageBox.showinfo("Port {}: \t Open".format(port))
            elif result == 61:
                tkmessageBox.showinfo("Port {}: \t Rejected by Host".format(port))
            else:
                tkmessageBox.showinfo("Port {}: \t Timed Out".format(port))
        
    def __close_connection_(self):
        pass
    def __del__(self):
        pass

class ResultsDialog(tk.Toplevel):
    def __init__(self, master, host_ip, host_name):
    
    
        self.window = Toplevel(master) 
        window.title("Result Dialog") 
        window.geometry("600x600") 
        window.mainloop()
        lbl10 = tkinter.Label(window, text = 'ScanID')
        lbl10.grid(row = 1, column = 1)
        lbl11 = tkinter.Label(window, text = 'Port Number')
        lbl11.grid(row = 1, column = 2)
        lbl12 = tkinter.Label(window, text = 'Is Open')
        lbl12.grid(row = 1, column = 3)
        lbl13 = tkinter.Label(window, text = 'Scan Time')
        lbl13.grid(row = 1, column = 4)
        
        #resuls
        try:
        con = sqlite3.connect('PortScanner.db') 
        cur = con.cursor()    
        cur.execute('SELECT ps.*,s.ScanStartTime FROM PortStatus ps JOIN Scan s on ps.ScanId = s.ScanId JOIN Host h on h.HostId = s.HostId WHERE h.HostIP = ? AND h.HostName = ?')
        data = cur.fetchall()         
        except lite.Error, e:
        sys.exit(1)   
        finally:
    
            if con:
            con.close()      
            self.window.destroy()
    

class PortScanner:
    max_port = 1023
    min_port = 0
    
    def __init__(self):
        pass
    def __init_gui(self):
        self.max_port = max_port
        self.min_port = min_port
        window = tkinter.tk() 
        window.title("Port Scanner") 
        window.geometry("300x300") 
        window.mainloop()
        lbl1 = tkinter.Label(window, text = 'Host IP')
        lbl1.grid(row = 1, column = 1)
        ent1 = tkinter.Entry(window)
        ent1.grid(row = 1, column = 2)
        lbl2 = tkinter.Label(window, text = 'Host Name')
        lbl2.grid(row = 2, column = 1)
        lbl3 = tkinter.Label(window, )
        lbl3.grid(row = 3, column = 1)
        def populate_label(label)
            label.config(text = socket.gethostbyaddr())
        populate_label(lbl3)   
        btn1 = tkinter.Button(window, text = 'Scan', command = __start_scanner)
        btn1.grid(row = 3, column = 2)
        btn2 = tkinter.Button(window, text = 'View Results', command = __view_results)
        btn2.grid(row = 4, column = 2)
        lbl4 = tkinter.Label(window, text = 'Scanner is Idle')
        lbl4.grid(row = 5)
        
    def __start_scanner(self):
        btn1.config(state=tk.DISABLED)
        print(socket.gethostname())
        start_scanner()
        
    def start_scanner(self):
        #much to do yet 
        scan_port()
    def scan_port(remoteServer, min_port, max_port):
        subprocess.call('clear', shell= True)
        #checking the time of the scan start 
        begin_time = datetime.now()
        #scanning
        try:
        remoteServerIP = socket.gethostbyname(remoteServer)
        for port in range(min_port, max_port):
            sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            sock.settimeout(1)
            result = sock.connect_ex((remoteServerIP, port))
            call_p_status = create_port_status() 
            sock.close()
    def __view_results(self):
        call_x = ResultsDialog(self.window) 
        self.window.wait_window(call_x.window)
    
    def __update_host_name(self):
        pass
if __name__ == '__main__':
    ps = PortScanner()